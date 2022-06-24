package org.llz.core.web.config;

import org.llz.annotation.spf.SpringFactoriesAuto;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 *
 */
@Configuration
@SpringFactoriesAuto
@ComponentScan({"org.llz.core.launcher.interceptor"})
public class WebBaseConfig {
}
