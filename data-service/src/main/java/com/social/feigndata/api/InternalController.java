package com.social.feigndata.api;

import com.nes.data.ScResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/data/b")
@Slf4j
public class InternalController {

    @GetMapping("/user/info")
    public ScResponse<Map<String, String>> userInfo() {
        Map<String, String> data = new HashMap<>();
        data.put("userName", "张三");
        data.put("age", "18");
        data.put("sex", "男");
        return new ScResponse<>(data);
    }
}
