package com.github.sulir.vcmapper.hardware;

import com.github.sulir.vcmapper.base.StringEnumerator;

import java.util.Set;

public class PositionValues implements StringEnumerator {
    @Override
    public Set<String> getValidValues() {
        return Set.of("topmost", "bottommost", "other");
    }
}
