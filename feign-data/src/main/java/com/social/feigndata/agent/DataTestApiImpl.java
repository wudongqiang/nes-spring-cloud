package com.social.feigndata.agent;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class DataTestApiImpl implements FallbackFactory<DataTestApi> {
    @Override
    public DataTestApi create(Throwable cause) {
        return new DataTestApi() {
            @Override
            public String userInfo() {
                return "{}";
            }
        };
    }
}
