package org.llz.gateway;

import org.llz.core.web.LlzSpringApplication;
import org.llz.core.web.constant.AppConstant;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GatewayApplication {
    public static void main(String[] args) {
        LlzSpringApplication.run(AppConstant.GATEWAY_SERVICE,GatewayApplication.class,args);
    }
}
