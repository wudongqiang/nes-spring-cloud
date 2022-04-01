package com.social.feigndata.agent;

import com.nes.data.ScResponse;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class FeignInternalApiImpl implements FallbackFactory<FeignInternalApi> {
    @Override
    public FeignInternalApi create(Throwable cause) {
        return new FeignInternalApi() {
            @Override
            public ScResponse<String> statistics(Long regAfterDay, String userType) {
                return new ScResponse<>();
            }
        };
    }
}
