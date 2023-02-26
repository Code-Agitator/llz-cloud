package org.llz.dynamic;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.llz.core.mp.config.LlzMybatisPlusConfig;
import org.llz.core.web.LlzSpringApplication;
import org.llz.common.constant.AppConstant;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@Slf4j
public class DynamicServiceApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = LlzSpringApplication.run(AppConstant.DYNAMIC_SERVICE, DynamicServiceApplication.class, args);
        log.info(run.getBean(LlzMybatisPlusConfig.class).toString());
    }
}
