package com.github.sulir.vcdemo.mapper.impl;

import com.github.sulir.vcdemo.mapper.exceptions.UnsupportedParameterTypeException;
import com.github.sulir.vcdemo.mapper.parameters.EnumConverter;
import com.github.sulir.vcdemo.mapper.parameters.NumberConverter;
import com.github.sulir.vcdemo.mapper.parameters.ParameterConverter;

public class MethodParameter {
    private static final ParameterConverter[] converters = {
            new NumberConverter(),
            new EnumConverter()
    };

    private Class type;
    private ParameterConverter converter;

    public MethodParameter(Class type) throws UnsupportedParameterTypeException {
        this.type = type;

        for (ParameterConverter converter : converters) {
            if (converter.isForType(type)) {
                this.converter = converter;
                return;
            }
        }

        throw new UnsupportedParameterTypeException(type);
    }

    public Object tryConversion(String term) {
        return converter.tryConversion(term, type);
    }
}
