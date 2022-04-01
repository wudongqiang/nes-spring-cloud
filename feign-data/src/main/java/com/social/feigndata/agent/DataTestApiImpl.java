package com.social.feigndata.agent;

import com.nes.data.ScResponse;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DataTestApiImpl implements FallbackFactory<DataTestApi> {
    @Override
    public DataTestApi create(Throwable cause) {
        return new DataTestApi() {
            @Override
            public ScResponse<Map<String, String>> userInfo() {
                return new ScResponse<>();
            }
        };
    }
}
