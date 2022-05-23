package org.llz.core.web.service;

import org.llz.annotation.spi.SPI;
import org.llz.core.web.LlzSpringApplication;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.Ordered;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.Assert;

import java.util.Properties;

@SPI
public interface LauncherService extends Ordered, Comparable<LauncherService> {


    /**
     * 启动时 处理 SpringApplicationBuilder
     *
     * @param builder SpringApplicationBuilder
     * @param appName SpringApplicationAppName
     * @param profile SpringApplicationProfile
     */
    void launcher(SpringApplicationBuilder builder, String appName, String profile);

    /**
     * 获取排列顺序
     *
     * @return order
     */
    @Override
    default int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    /**
     * 对比排序
     *
     * @param o LauncherService
     * @return compare
     */
    @Override
    default int compareTo(LauncherService o) {
        return Integer.compare(this.getOrder(), o.getOrder());
    }


    default void loadFromResource(String fileName, Properties props) {
        YamlPropertiesFactoryBean factoryBean = new YamlPropertiesFactoryBean();
        factoryBean.setResources(new ClassPathResource(fileName, LlzSpringApplication.class.getClassLoader()));
        Properties object = factoryBean.getObject();
        Assert.notNull(object, String.format("基础配置文件({%s})为null", fileName));
        object.keySet().forEach(key -> props.setProperty(key.toString(), object.getProperty(key.toString())));
    }
}
