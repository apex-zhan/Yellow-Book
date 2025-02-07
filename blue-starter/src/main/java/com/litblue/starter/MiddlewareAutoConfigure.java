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
})
public class
MiddlewareAutoConfigure {
}
