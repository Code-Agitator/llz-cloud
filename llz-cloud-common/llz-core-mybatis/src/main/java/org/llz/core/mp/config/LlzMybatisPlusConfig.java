package org.llz.core.mp.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("org.llz.**.mapper.**")
public class LlzMybatisPlusConfig {
}
