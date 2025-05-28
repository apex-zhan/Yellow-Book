package com.litblue.gateway.filter;

import cn.hutool.core.text.AntPathMatcher;
import com.alibaba.fastjson2.JSON;
import com.litblue.gateway.config.AuthConfig;
import com.litblue.starter.cache.redis.RedisCache;
import com.litblue.starter.cache.redis.RedisKeys;
import com.litblue.starter.core.AjaxResult;
import lombok.AllArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author MECHREVO
 * 网关的请求进行鉴权过滤处理
 */
@Component
@AllArgsConstructor
public class ForwardAuthFilter implements GlobalFilter, Ordered {
    private final AuthConfig authConfig;

    private RedisCache redisCache;

    //基于 Ant 风格的路径模式匹配，常用于 URL 路径匹配、文件路径匹配等场景
    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    /**
     * 对进入网关的请求进行鉴权过滤处理
     *
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        //过滤请求路径
        if (isExclude(request.getPath().toString())) {
            //放行
            return chain.filter(exchange);
        }
        // 获取请求头信息
        HttpHeaders headers = exchange.getRequest().getHeaders();
        String token = null;
        //Authorization是我们的token命名规则（在user模块中配置了sa-token）
        List<String> authorization = headers.get("Authorization");
        if (!authorization.isEmpty() && authorization != null) {
            // 取第一个值作为令牌，通常情况下 "Authorization" 字段只有一个值
            token = headers.get("Authorization").get(0);
        }
        //用redis来进行网关鉴权，鉴权网关过来的token是不是能和redis中匹配
//        redis里存放的是用户id值
        Long userId = this.redisCache.getCacheObject(RedisKeys.DEFINE_TOKEN);
        if (userId == null) {
            return handleUnauthorized(response);
        }
        //传递用户信息；若成功从 Redis 缓存中获取到 userId，即用户通过鉴权，使用 ServerWebExchange 的 mutate 方法，在请求头中添加名为 user-info 的字段，并将 userId 转换为字符串后作为该字段的值。
        // 然后构建新的 ServerWebExchange 对象，并将其传递给过滤器链的 chain.filter 方法，让后续过滤器可以获取到该用户信息并继续处理请求。
        ServerWebExchange swe = exchange.mutate().request(builder -> builder.header("user-info", String.valueOf(userId))).build();
        return chain.filter(swe);
    }


    /**
     * 处理未授权，返回一个包含错误信息的 JSON 响应
     *
     * @param response
     * @return
     */
    private Mono<Void> handleUnauthorized(ServerHttpResponse response) {
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        AjaxResult ajaxResult = new AjaxResult<>("鉴权失败", "401");
        String body = JSON.toJSONString(ajaxResult);
        // 创建一个 DataBuffer，将 JSON 字符串转换为字节数组并包装
        DataBuffer buffer = response.bufferFactory().wrap(body.getBytes(StandardCharsets.UTF_8));
        // 将包含错误信息的 JSON 响应写入到响应体中，并返回处理完成后的 Mono 对象
        return response.writeWith(Mono.just(buffer));
    }


    /**
     * 不需要拦截的路径path
     */
    private boolean isExclude(String path) {
        for (String excludePath : authConfig.getExcludePaths()) {
            if (antPathMatcher.match(excludePath, path)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 实现 Ordered 接口的 getOrder 方法，返回过滤器的执行顺序，值越小优先级越高
     *
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
