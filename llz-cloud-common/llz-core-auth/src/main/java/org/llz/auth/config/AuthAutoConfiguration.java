package org.llz.auth.config;


import lombok.AllArgsConstructor;
import org.llz.annotation.spf.SpringFactoriesAuto;
import org.llz.auth.properties.JwtTokenManageProperties;
import org.llz.auth.service.TokenManageService;
import org.llz.auth.service.impl.JwtTokenManageServiceImpl;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(JwtTokenManageProperties.class)
@SpringFactoriesAuto
@AllArgsConstructor
public class AuthAutoConfiguration {
    JwtTokenManageProperties jwtTokenManageProperties;

    @Bean
    public TokenManageService tokenManageService() {
        return new JwtTokenManageServiceImpl(jwtTokenManageProperties);
    }


}
