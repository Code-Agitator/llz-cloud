package org.llz.gateway.config;

import com.alibaba.nacos.common.notify.NotifyCenter;
import org.llz.gateway.listener.ServiceInstanceChangeListener;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Service;

@Configuration
public class NacosListenerConfig {
    @Bean
    public ServiceInstanceChangeListener instanceChangeListener(ApplicationContext context) {
        ServiceInstanceChangeListener listener = new ServiceInstanceChangeListener(context);
        NotifyCenter.registerSubscriber(listener);
        return listener;
    }
}
