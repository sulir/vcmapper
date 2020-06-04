package com.github.sulir.vcmapper.base;

public interface Converter<T> extends StringTester {
    T convert(String term);
}
