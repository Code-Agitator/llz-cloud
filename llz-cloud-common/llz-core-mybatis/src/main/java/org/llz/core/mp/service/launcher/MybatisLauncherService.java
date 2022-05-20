package org.llz.core.mp.service.launcher;

import org.llz.annotation.base.SPIAuto;
import org.llz.core.mp.constant.MybatisPlusConstant;
import org.llz.core.web.LlzSpringApplication;
import org.llz.core.web.service.LauncherService;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.Assert;

import java.util.Properties;

@SPIAuto
public class MybatisLauncherService implements LauncherService {
    @Override
    public void launcher(SpringApplicationBuilder builder, String appName, String profile) {
        Properties props = System.getProperties();
        YamlPropertiesFactoryBean factoryBean = new YamlPropertiesFactoryBean();
        factoryBean.setResources(new ClassPathResource("application-mybatis-base.yml", LlzSpringApplication.class.getClassLoader()));
        Properties object = factoryBean.getObject();
        Assert.notNull(object, "基础配置文件(application-mybatis-base.yml)为null");
        object.keySet().forEach(key -> props.setProperty(key.toString(), object.getProperty(key.toString())));
        if (MybatisPlusConstant.logEnable(profile)) {
            props.setProperty("mybatis-plus.configuration.log-impl", "org.apache.ibatis.logging.stdout.StdOutImpl");
        }
    }

    @Override
    public int getOrder() {
        return LauncherService.super.getOrder();
    }
}
