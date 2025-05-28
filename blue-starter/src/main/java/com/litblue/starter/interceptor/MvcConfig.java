package com.litblue.starter.interceptor;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ConditionalOnClass(DispatcherServlet.class)
/**
 * MVC配置类，用于配置拦截器
 *
 * @ConditionalOnClass(DispatcherServlet.class)
 * 只有在类路径中存在DispatcherServlet时才会创建这个配置类的实例
 * bean生效的先后顺序
 */
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserInterceptor())
                // 排除路径，不拦截
                .excludePathPatterns("/user/info/loginUser", "/user/info/genPhoneCode","/user/info/logout");
    }
}
