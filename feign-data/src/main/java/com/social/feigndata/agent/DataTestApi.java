package com.social.feigndata.agent;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "data-service", path = "/data/b", fallbackFactory = DataTestApiImpl.class)
public interface DataTestApi {

    @GetMapping("/user/info")
    String userInfo();
}
