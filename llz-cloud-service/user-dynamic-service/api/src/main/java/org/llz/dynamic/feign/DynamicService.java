package org.llz.dynamic.feign;

import org.llz.common.constant.AppConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(AppConstant.DYNAMIC_SERVICE)
public interface DynamicService {
    @GetMapping("/hello")
    String hello();
}
