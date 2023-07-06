package com.example.beday6.config.jwt;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("jwt") // application.yml 참조
public class JwtProperties {
    private String issuer;
    private String secret;


}
