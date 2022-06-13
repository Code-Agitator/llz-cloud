package org.llz.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("job")
@Data
public class XxlJobProperties {

    @Value("${server.admin.addresses}")
    private String adminAddresses;

    @Value("${server.accessToken}")
    private String accessToken;

    @Value("${server.executor.appname}")
    private String appname;

    @Value("${server.executor.address}")
    private String address;

    @Value("${server.userName}")
    private String userName;

    @Value("${server.password}")
    private String password;

    @Value("${server.jobGroup}")
    private String jobGroup;
}
