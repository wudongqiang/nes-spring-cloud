package com.social.feigndata.agent;

import com.nes.data.ScResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@FeignClient(value = "data-service", path = "/data/b", fallbackFactory = DataTestApiImpl.class)
public interface DataTestApi {

    @GetMapping("/user/info")
    ScResponse<Map<String, String>> userInfo();
}
