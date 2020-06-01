package com.github.sulir.vcmapper.api;

public interface StringConverter<T> extends ValueCondition {
    T convert(String term);
}
