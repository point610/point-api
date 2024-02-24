package com.point.apisdk;

import com.point.apisdk.client.PointApiClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 客户端配置
 */
@Configuration
@ConfigurationProperties("pointapi.client")
@Data
@ComponentScan
public class PointApiConfig {

    private String accessKey;

    private String secretKey;

    @Bean
    public PointApiClient pointApiClient() {
        return new PointApiClient(accessKey, secretKey);
    }

}
