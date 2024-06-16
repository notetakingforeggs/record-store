package com.northcoders.jv_recordshop.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "app.config")
public class JWTProperties {
    private String secretKey;
}
