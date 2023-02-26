package org.llz.cloud.user;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.json.JSONUtil;
import llz.cloud.core.test.LlzSpringBootTest;
import org.junit.jupiter.api.Test;
import org.llz.common.constant.AppConstant;
import org.llz.job.entity.XxlJobInfo;
import org.llz.job.service.XxlJobService;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@LlzSpringBootTest(appName = AppConstant.USER_SERVICE, classes = UserServiceApplication.class)
public class JobTest {
    @Resource
    XxlJobService jobService;

    @Test
    void testJob() {
        XxlJobInfo xxlJobInfo = XxlJobInfo.builder()
                .jobGroup(1)
                .jobDesc("hello")
                .author("llz")
                .scheduleType("CRON")
                .scheduleConf("0 0 0 * * ?")
                .glueType("BEAN")
                .executorHandler("demoJobHandler")
                .executorRouteStrategy("FIRST")
                .misfireStrategy("DO_NOTHING")
                .executorBlockStrategy("SERIAL_EXECUTION")
                .build();

        String add = jobService.add(xxlJobInfo);
        System.out.println(JSONUtil.parse(add));
    }

    @Test
    void testRunJob() {
        ThreadUtil.sleep(10, TimeUnit.SECONDS);
        System.out.println("启动1：" + jobService.run(1));
        System.out.println("启动2：" + jobService.run(2));
        while (true) {
            ThreadUtil.sleep(2, TimeUnit.SECONDS);
        }
    }
}
