package org.llz.auth.config;


import lombok.AllArgsConstructor;
import org.llz.auth.properties.JwtTokenManageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(JwtTokenManageProperties.class)
@AllArgsConstructor
public class AuthAutoConfiguration {
    JwtTokenManageProperties jwtTokenManageProperties;

}
