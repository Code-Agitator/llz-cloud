package org.llz.job.config;

import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.llz.annotation.spf.SpringFactoriesAuto;
import org.llz.job.service.XxlJobService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.llz.config.XxlJobProperties;

@Configuration
@SpringFactoriesAuto
@Slf4j
@Getter
@EnableConfigurationProperties(XxlJobProperties.class)
@AllArgsConstructor
public class XxlJobAutoConfiguration {
    XxlJobProperties xxlJobProperties;

    @Bean
    public XxlJobSpringExecutor xxlJobExecutor() {
        log.info(">>>>>>>>>>> job-server config init.");
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        xxlJobSpringExecutor.setAdminAddresses(xxlJobProperties.getAdminAddresses());
        xxlJobSpringExecutor.setAppname(xxlJobProperties.getAppname());
        xxlJobSpringExecutor.setAddress(xxlJobProperties.getAddress());
        xxlJobSpringExecutor.setAccessToken(xxlJobProperties.getAccessToken());
        return xxlJobSpringExecutor;
    }

    @Bean
    @ConditionalOnMissingBean(XxlJobService.class)
    public XxlJobService xxlJobService() {
        return new XxlJobService(
                xxlJobProperties.getAdminAddresses(),
                xxlJobProperties.getAppname(),
                xxlJobProperties.getUserName(),
                xxlJobProperties.getPassword()
        );
    }

}
