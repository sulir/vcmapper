package com.github.sulir.vcdemo.sample;

import com.github.sulir.vcdemo.mapper.api.ValueSet;

import java.util.Set;

public class PositionValues implements ValueSet {
    @Override
    public Set<String> getValues() {
        return Set.of("topmost", "bottommost", "other");
    }
}
