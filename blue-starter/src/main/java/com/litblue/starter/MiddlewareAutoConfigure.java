package com.litblue.starter;


import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 支撑类，加载bean
 */
@Configuration
@AutoConfigurationPackage
@ComponentScan(basePackages = {
        "com.litblue.starter.advice",
        "com.litblue.starter.cache",
        "com.litblue.starter.sql",
        "com.litblue.starter.interceptor",
        "com.litblue.starter.http",
        "com.litblue.starter.async"
})
public class
MiddlewareAutoConfigure {
}
