package com.litblue.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author MECHREVO
 * 排除路径
 */
@Component
@ConfigurationProperties(prefix = "blue.auth")
//将配置文件（application.yml）中以blue.auth为前缀的属性值映射到这个类的字段上
@Data
public class AuthConfig {
    private List<String> excludePaths;

    public List<String> getExcludePaths() {
        return excludePaths;
    }
}
