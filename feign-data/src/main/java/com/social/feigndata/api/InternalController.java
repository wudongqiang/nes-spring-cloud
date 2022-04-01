package com.social.feigndata.api;

import com.nes.data.ScBaseDataType;
import com.nes.data.ScResponse;
import com.social.feigndata.agent.DataTestApi;
import com.social.feigndata.agent.FeignInternalApi;
import com.social.feigndata.aspect.ScRateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/feign/a")
@Slf4j
public class InternalController {

    @Value("${nes.data}")
    private String data;

    @Autowired
    private FeignInternalApi feignInternalApi;
    @Autowired
    private DataTestApi dataTestApi;

    @GetMapping("/test/yrt/statistics")
    public ScResponse<String> statistics() {
        return feignInternalApi.statistics(12L, "CHANNEL");
    }

    @GetMapping("/show")
    @ScRateLimiter(limit = 1, expire = 10)
    public ScResponse<ScBaseDataType<String>> show(@RequestParam(required = false) String p) {
        log.info("请求参数：{}", p);
        return new ScResponse<>(ScBaseDataType.of("展示：" + data + "," + p));
    }

    @GetMapping("/userInfo")
    public ScResponse<Map<String, String>>  userInfo() {
        log.info("------------userInfo-----------------");
        return dataTestApi.userInfo();
    }
}
