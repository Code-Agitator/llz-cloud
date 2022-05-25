package org.llz.core.web;

import org.llz.annotation.spi.launcher.LauncherService;
import org.llz.common.constant.AppConstant;
import org.llz.core.web.execption.SpringApplicationBuildException;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.*;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

public class LlzSpringApplication {
    private LlzSpringApplication() {
    }

    /**
     * Create an application context
     * java -jar app.jar --spring.profiles.active=prod --server.port=2333
     *
     * @param appName application name
     * @param source  The sources
     * @return an application context created from the current state
     */
    public static ConfigurableApplicationContext run(String appName, Class<?> source, String... args) {
        SpringApplicationBuilder builder = createSpringApplicationBuilder(appName, source, args);
        return builder.run(args);
    }

    /**
     * 自定义构造SpringApplicationBuilder
     */
    private static SpringApplicationBuilder createSpringApplicationBuilder(String appName, Class<?> source, String[] args) throws RuntimeException {
        // 读取环境变量，使用spring boot的规则
        ConfigurableEnvironment environment = new StandardEnvironment();
        MutablePropertySources propertySources = environment.getPropertySources();
        propertySources.addFirst(new SimpleCommandLinePropertySource(args));
        propertySources.addLast(new MapPropertySource(StandardEnvironment.SYSTEM_PROPERTIES_PROPERTY_SOURCE_NAME, environment.getSystemProperties()));
        propertySources.addLast(new SystemEnvironmentPropertySource(StandardEnvironment.SYSTEM_ENVIRONMENT_PROPERTY_SOURCE_NAME, environment.getSystemEnvironment()));
        // 获取配置的环境变量
        String[] activeProfiles = environment.getActiveProfiles();
        // 判断环境:dev、test、prod
        List<String> profiles = Arrays.stream(activeProfiles).collect(Collectors.toList());
        SpringApplicationBuilder builder = new SpringApplicationBuilder(source);
        String profile;
        if (profiles.isEmpty()) {
            // 默认dev开发
            profile = AppConstant.DEV_CODE;
            profiles.add(profile);
            builder.profiles(profile);
        } else if (profiles.size() == 1) {
            profile = profiles.get(0);
        } else {
            // 同时存在多个环境
            throw new SpringApplicationBuildException("同时存在环境变量:[" + StringUtils.arrayToCommaDelimitedString(activeProfiles) + "]");
        }


        // 加载自定义组件
        List<LauncherService> launcherList = new ArrayList<>();
        ServiceLoader.load(LauncherService.class).forEach(launcherList::add);
        launcherList.stream().sorted(Comparator.comparing(LauncherService::getOrder)).collect(Collectors.toList())
                .forEach(launcherService -> launcherService.launcher(builder, appName, profile));
        return builder;

    }
}
