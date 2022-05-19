package org.llz.cloud;

import org.llz.core.web.LlzSpringApplication;
import org.llz.core.web.constant.AppConstant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserServiceApplication {
    public static void main(String[] args) {
        LlzSpringApplication.run(AppConstant.USER_SERVICE,UserServiceApplication.class, args);
    }
}
