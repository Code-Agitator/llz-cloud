package org.llz.dynamic.config;

import lombok.extern.slf4j.Slf4j;
import org.llz.annotation.spf.SpringFactoriesAuto;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients("org.llz.dynamic.feign")
@SpringFactoriesAuto
@Slf4j
public class FeignClientApiAutoConfig {
}
