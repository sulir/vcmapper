package com.github.sulir.vcmapper.home;

import com.github.sulir.vcmapper.api.ValueSet;

import java.util.Set;

public class PossibleObjects implements ValueSet {
    @Override
    public Set<String> getValues() {
        return Set.of("newspaper", "juice", "shoes", "socks");
    }
}
