package org.llz.core.web.service.launcher;

import org.apache.http.util.Asserts;
import org.llz.annotation.base.SPIAuto;
import org.llz.core.web.LlzSpringApplication;
import org.llz.core.web.constant.LauncherConstant;
import org.llz.core.web.service.LauncherService;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.Assert;

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
        YamlPropertiesFactoryBean factoryBean = new YamlPropertiesFactoryBean();
        factoryBean.setResources(new ClassPathResource("application-base.yml", LlzSpringApplication.class.getClassLoader()));
        Properties object = factoryBean.getObject();
        Assert.notNull(object, "基础配置文件(application-base.yml)为null");
        object.keySet().forEach(key -> props.setProperty(key.toString(), object.getProperty(key.toString())));
    }
}