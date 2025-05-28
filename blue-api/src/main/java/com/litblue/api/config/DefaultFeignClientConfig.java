package com.litblue.api.config;

import com.litblue.api.failback.ArtWorkClientFailBackFactory;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import utils.UserContext;

/**
 * @author MECHREVO
 */
@Configuration
public class DefaultFeignClientConfig {
    @Bean
    public ArtWorkClientFailBackFactory getArtWorkClientFailBackFactory() {
        return new ArtWorkClientFailBackFactory();
    }

    private static final Logger logger = LoggerFactory.getLogger(DefaultFeignClientConfig.class);

    /**
     * 拦截器，用于在请求头中添加用户信息
     */
    @Bean
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate template) {
                try {
                    Long userId = UserContext.getUser();
                    if (userId != null) {
                        template.header("user-info", userId.toString());
                        logger.info("Added user ID {} to request header", userId);
                    }
                } catch (Exception e) {
                    logger.error("Error getting user ID from UserContext", e);
                }
            }
        };
    }
}

