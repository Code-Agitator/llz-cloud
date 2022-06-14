package org.llz.cloud.user.service.impl;

import cn.hutool.core.thread.ThreadUtil;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class JobTestServiceImpl {
    @XxlJob("demoJobHandler")
    public void demoJobHandler() throws Exception {
        XxlJobHelper.log("XXL-JOB, Hello World.");
        for (int i = 0; i < 500; i++) {
            XxlJobHelper.log("beat at:" + i);
            log.info("Thread:{} log:执行第{}次", Thread.currentThread().getName(), i);
            ThreadUtil.sleep(2, TimeUnit.SECONDS);
        }
    }
}
