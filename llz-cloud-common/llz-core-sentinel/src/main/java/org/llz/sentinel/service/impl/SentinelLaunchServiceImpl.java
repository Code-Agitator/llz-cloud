package org.llz.sentinel.service.impl;

import org.llz.annotation.spi.SPIAuto;
import org.llz.annotation.spi.launcher.LauncherService;
import org.llz.sentinel.constant.SentinelConstant;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.Properties;

@SPIAuto
public class SentinelLaunchServiceImpl implements LauncherService {
    @Override
    public void launcher(SpringApplicationBuilder builder, String appName, String profile) {
        Properties props = System.getProperties();
        loadFromResource(SentinelConstant.SENTINEL_CONFIG_FILE, props);
    }
}
