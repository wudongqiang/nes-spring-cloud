package com.socialcredits.gateservice.config;

import com.netflix.loadbalancer.AbstractServerPredicate;
import com.netflix.loadbalancer.AvailabilityPredicate;
import com.netflix.loadbalancer.CompositePredicate;
import com.netflix.loadbalancer.PredicateBasedRule;

public class MyCustomRule extends PredicateBasedRule {

    private final CompositePredicate predicate;

    public MyCustomRule() {
        this.predicate = createCompositePredicate(new MetadataAwarePredicate(), new AvailabilityPredicate(this, null));
    }

    private CompositePredicate createCompositePredicate(DiscoveryEnabledPredicate discoveryEnabledPredicate, AvailabilityPredicate availabilityPredicate) {
        return CompositePredicate.withPredicates(discoveryEnabledPredicate, availabilityPredicate)
                .build();
    }

    @Override
    public AbstractServerPredicate getPredicate() {
        return predicate;
    }

}