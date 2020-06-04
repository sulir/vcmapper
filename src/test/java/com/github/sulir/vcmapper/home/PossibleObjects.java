package com.github.sulir.vcmapper.home;

import com.github.sulir.vcmapper.base.StringEnumerator;

import java.util.Set;

public class PossibleObjects implements StringEnumerator {
    @Override
    public Set<String> getValidValues() {
        return Set.of("newspaper", "juice", "shoes", "socks");
    }
}
