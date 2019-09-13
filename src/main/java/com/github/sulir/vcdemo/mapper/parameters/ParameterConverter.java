package com.github.sulir.vcdemo.mapper.parameters;

public interface ParameterConverter {
    boolean isForType(Class type);
    Object tryConversion(String term, Class type);
}
