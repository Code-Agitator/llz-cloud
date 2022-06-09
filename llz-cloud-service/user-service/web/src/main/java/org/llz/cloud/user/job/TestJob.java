package org.llz.cloud.user.job;

import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.stereotype.Component;

@Component
public class TestJob {
    @XxlJob("hello")
    public void printHello() {
        System.out.println("Hello");
    }
}
