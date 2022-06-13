package llz.cloud.core.test;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.llz.annotation.spi.launcher.LauncherService;
import org.llz.core.web.constant.LauncherConstant;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;
import java.util.stream.Collectors;

public class LlzSpringExtension extends SpringExtension {
    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        super.beforeAll(context);
        setUpTestClass(context);
    }

    private void setUpTestClass(ExtensionContext context) {
        Class<?> clazz = context.getRequiredTestClass();
        LlzSpringBootTest llzSpringBootTest = clazz.getAnnotation(LlzSpringBootTest.class);

        if (llzSpringBootTest == null) {
            throw new LlzSpringBootTestException(String.format("%s must be @BladeBootTest .", clazz));
        }
        String appName = llzSpringBootTest.appName();
        String profile = llzSpringBootTest.profile();
        Properties props = System.getProperties();
        props.setProperty("spring.application.name", appName);
        props.setProperty("spring.profiles.active", profile);
        props.setProperty("spring.cloud.nacos.discovery.server-addr", LauncherConstant.nacosAddr(profile));
        props.setProperty("spring.cloud.nacos.config.server-addr", LauncherConstant.nacosAddr(profile));
        props.setProperty("spring.cloud.nacos.config.prefix", LauncherConstant.nacosAddr(profile));
        props.setProperty("spring.cloud.nacos.config.file-extension", "yml");
        // 加载自定义组件
        if (llzSpringBootTest.enableLoader()) {
            List<LauncherService> launcherList = new ArrayList<>();
            SpringApplicationBuilder builder = new SpringApplicationBuilder(clazz);
            ServiceLoader.load(LauncherService.class).forEach(launcherList::add);
            launcherList.stream().sorted(Comparator.comparing(LauncherService::getOrder)).collect(Collectors.toList())
                    .forEach(launcherService -> launcherService.launcher(builder, appName, profile));
        }
        System.err.printf("---[junit.test]:[%s]---启动中，读取到的环境变量:[%s]%n", appName, profile);
    }
}
