package org.llz.core.launcher.service.launcher;

import org.llz.annotation.spi.SPIAuto;
import org.llz.annotation.spi.launcher.LauncherService;
import org.llz.core.launcher.constant.LauncherConstant;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.Properties;

@SPIAuto
public class BaseLauncherService implements LauncherService {
    @Override
    public void launcher(SpringApplicationBuilder builder, String appName, String profile) {
        Properties props = System.getProperties();
        props.setProperty("spring.cloud.nacos.discovery.server-addr", LauncherConstant.nacosAddr(profile));
        props.setProperty("spring.cloud.nacos.config.server-addr", LauncherConstant.nacosAddr(profile));
        props.setProperty("spring.application.name", appName);
        // 共享配置文件
        props.setProperty("spring.cloud.nacos.config.shared-dataids", String.join(",", LauncherConstant.getShareConfigFile()));


        // 设置基础的系统参数
        loadFromResource(LauncherConstant.LAUNCHER_CONFIG_FILE, props);
    }
}
