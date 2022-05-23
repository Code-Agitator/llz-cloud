package org.llz.core.mp.config;

import org.llz.annotation.spf.SpringFactoriesAuto;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("org.llz.**.mapper.**")
@SpringFactoriesAuto
public class LlzMybatisPlusConfig {
}
