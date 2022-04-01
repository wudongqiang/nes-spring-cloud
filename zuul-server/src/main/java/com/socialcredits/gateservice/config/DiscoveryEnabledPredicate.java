package com.socialcredits.gateservice.config;

import com.netflix.loadbalancer.AbstractServerPredicate;
import com.netflix.loadbalancer.PredicateKey;
import com.netflix.niws.loadbalancer.DiscoveryEnabledServer;

/**
 *  通过断言的方式寻找可用的服务
 */
public abstract class DiscoveryEnabledPredicate extends AbstractServerPredicate {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean apply(PredicateKey input) {
        return input != null
                && input.getServer() instanceof DiscoveryEnabledServer
                && apply((DiscoveryEnabledServer) input.getServer());
    }

    /**
     * Returns whether the specific {@link DiscoveryEnabledServer} matches this predicate.
     *
     * @param server the discovered server
     * @return whether the server matches the predicate
     */
    protected abstract boolean apply(DiscoveryEnabledServer server);
}