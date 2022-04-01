package com.social.feigndata.agent;


import com.nes.data.ScResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: zhaopeng.chen
 * @date: 17-12-25 下午2:28
 */
@FeignClient(name = "agent-user",
        url = "https://test-yrt.yucunkeji.com",
        path = "/api/agent/user",fallbackFactory = FeignInternalApiImpl.class)
public interface FeignInternalApi {

    @GetMapping("/internal/user/statistics")
    ScResponse<String> statistics(@RequestParam("regAfterDay") Long regAfterDay,
                                          @RequestParam("userType") String userType);
}
