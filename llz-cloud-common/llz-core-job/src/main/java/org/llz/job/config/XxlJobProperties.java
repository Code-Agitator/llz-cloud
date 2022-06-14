package org.llz.job.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("job")
@Data
public class XxlJobProperties {

    @Value("${job.server.addresses}")
    private String adminAddresses;

    @Value("${job.server.accessToken}")
    private String accessToken;

    @Value("${spring.application.name}")
    private String appname;

    //    @Value("${job.server.executor.address}")
    private String address;

    @Value("${job.server.username}")
    private String username;

    @Value("${job.server.password}")
    private String password;

    @Value("${job.server.jobGroup}")
    private String jobGroup;
}
