package com.github.sulir.vcmapper.hardware;

import com.github.sulir.vcmapper.api.ValueSet;

import java.util.Set;

public class PositionValues implements ValueSet {
    @Override
    public Set<String> getValues() {
        return Set.of("topmost", "bottommost", "other");
    }
}
