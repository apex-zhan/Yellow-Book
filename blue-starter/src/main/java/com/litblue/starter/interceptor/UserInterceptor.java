package com.litblue.starter.interceptor;


import exception.UnauthorizedException;
import org.springframework.web.servlet.HandlerInterceptor;
import utils.UserContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserInterceptor implements HandlerInterceptor {

    /**
     * 请求之前
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        // 检查请求头中是否包含"user-info"字段 从网关获取user-info
        String userInfo = request.getHeader("user-info");
        // 如果"user-info"字段不存在，则抛出未经授权的异常
        if (userInfo == null || userInfo.isEmpty()) {
            throw new UnauthorizedException("鉴权失败");
        }
        UserContext.setUser(Long.valueOf(userInfo));
        // 如果所有条件都通过，则返回true，表示允许处理该请求
        return true;
    }

    /**
     * 请求之后
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 清理用户信息
        UserContext.removeUser();
    }
}