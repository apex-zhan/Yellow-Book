package com.litblue.user;


import com.litblue.api.config.DefaultFeignClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author MECHREVO
 */
@SpringBootApplication
@EnableFeignClients(value = "com.litblue.api.client", defaultConfiguration = DefaultFeignClientConfig.class)
public class LitUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(LitUserApplication.class, args);
    }
}