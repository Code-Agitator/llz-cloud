package org.llz.sentinel.config;

import com.alibaba.csp.sentinel.SphU;
import feign.Feign;
import org.llz.annotation.spf.SpringFactoriesAuto;
import org.llz.sentinel.feign.CustomSentinelFeign;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

@Configuration(proxyBeanMethods = false)
@ConditionalOnClass({SphU.class, Feign.class})
@SpringFactoriesAuto
public class SentinelFeignAutoConfiguration {

    @Bean
    @Scope("prototype")
    @ConditionalOnMissingBean
    @Primary
    @ConditionalOnProperty(name = "feign.sentinel.enabled.tmp")
    public Feign.Builder feignSentinelBuilder() {
        return CustomSentinelFeign.builder();
    }


}
