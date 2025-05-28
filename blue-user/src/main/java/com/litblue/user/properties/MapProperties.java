package com.litblue.user.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "blue.map")
public class MapProperties {
    // 地图密钥
    private String key;
    // 地图服务地址#周边推荐
    private String perimeterUrl;
    //搜索半径
    private int radius;
}
