package org.llz.job.config;

import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.llz.annotation.spf.SpringFactoriesAuto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringFactoriesAuto
@Slf4j
@Getter
public class XxlJobAutoConfiguration {

    @Value("${job.server.admin.addresses}")
    private String adminAddresses;

    @Value("${job.server.accessToken}")
    private String accessToken;

    @Value("${job.server.executor.appname}")
    private String appname;

    @Value("${job.server.executor.address}")
    private String address;

    @Value("${job.server.userName}")
    private String userName;

    @Value("${job.server.password}")
    private String password;

    @Value("${job.server.jobGroup}")
    private String jobGroup;

    @Bean
    public XxlJobSpringExecutor xxlJobExecutor() {
        log.info(">>>>>>>>>>> job-server config init.");
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        xxlJobSpringExecutor.setAdminAddresses(adminAddresses);
        xxlJobSpringExecutor.setAppname(appname);
        xxlJobSpringExecutor.setAddress(address);
        xxlJobSpringExecutor.setAccessToken(accessToken);

        return xxlJobSpringExecutor;
    }

}
