package com.socialcredits.gateservice.config;

import com.netflix.niws.loadbalancer.DiscoveryEnabledServer;
import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

/**
 * 通过获取eureka.instance.metadata-map:配置信息值 进行判断
 */
public class MetadataAwarePredicate extends DiscoveryEnabledPredicate {

    private static final String KEY = "version";

    @Override
    protected boolean apply(DiscoveryEnabledServer server) {
        Map<String, String> metadata = server.getInstanceInfo().getMetadata();
        if (!metadata.containsKey(KEY)) {
            return true;
        }
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String version = request.getHeader(KEY);
        return Objects.equals(version, metadata.get(KEY));
    }
}