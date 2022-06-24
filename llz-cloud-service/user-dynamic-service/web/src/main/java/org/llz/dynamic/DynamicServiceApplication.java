package org.llz.dynamic;

import org.llz.core.launcher.LlzSpringApplication;
import org.llz.common.constant.AppConstant;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DynamicServiceApplication {
    public static void main(String[] args) {
        LlzSpringApplication.run(AppConstant.DYNAMIC_SERVICE, DynamicServiceApplication.class, args);
    }
}
