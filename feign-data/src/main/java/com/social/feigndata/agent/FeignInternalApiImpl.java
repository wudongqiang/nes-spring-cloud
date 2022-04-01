package com.social.feigndata.agent;

import feign.hystrix.FallbackFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class FeignInternalApiImpl implements FallbackFactory<FeignInternalApi> {
    @Override
    public FeignInternalApi create(Throwable cause) {
        return new FeignInternalApi() {
            @Override
            public ResponseEntity<Object> statistics(Long regAfterDay, String userType) {
                return ResponseEntity.ok(null);
            }
        };
    }
}
