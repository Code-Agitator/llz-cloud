package org.llz.core.mp.service.launcher;

import org.llz.annotation.spi.SPIAuto;
import org.llz.core.mp.constant.MybatisPlusConstant;
import org.llz.core.web.service.LauncherService;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.Properties;

@SPIAuto
public class MybatisLauncherService implements LauncherService {
    @Override
    public void launcher(SpringApplicationBuilder builder, String appName, String profile) {
        Properties props = System.getProperties();
        loadFromResource(MybatisPlusConstant.MYBATIS_CONFIG_BASE_FILE, props);
        if (MybatisPlusConstant.logEnable(profile)) {
            props.setProperty("mybatis-plus.configuration.log-impl", "org.apache.ibatis.logging.stdout.StdOutImpl");
        }
    }

    @Override
    public int getOrder() {
        return LauncherService.super.getOrder();
    }
}
