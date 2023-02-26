package org.llz.cloud.user;

import org.llz.core.launcher.LlzSpringApplication;
import org.llz.common.constant.AppConstant;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserServiceApplication {
    public static void main(String[] args) {
        LlzSpringApplication.run(AppConstant.USER_SERVICE, UserServiceApplication.class, args);
    }
}
