package org.llz.gateway;

import org.llz.core.launcher.LlzSpringApplication;
import org.llz.common.constant.AppConstant;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GatewayApplication {
    public static void main(String[] args) {
        LlzSpringApplication.run(AppConstant.GATEWAY_SERVICE, GatewayApplication.class, args);
    }
}
