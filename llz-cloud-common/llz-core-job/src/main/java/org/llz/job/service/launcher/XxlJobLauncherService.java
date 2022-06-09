package org.llz.job.service.launcher;

import org.llz.annotation.spi.SPIAuto;
import org.llz.annotation.spi.launcher.LauncherService;
import org.llz.job.constant.XxlJobConstant;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.Properties;

@SPIAuto
public class XxlJobLauncherService implements LauncherService {

    @Override
    public void launcher(SpringApplicationBuilder builder, String appName, String profile) {
        Properties props = System.getProperties();
        loadFromResource(XxlJobConstant.XXL_JOB_CONFIG_BASE_FILE, props);
    }
}
