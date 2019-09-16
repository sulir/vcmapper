package com.github.sulir.vcmapper.parameters;

import java.lang.reflect.Parameter;

public class NumberConverter implements ParameterConverter {
    @Override
    public boolean isForParameter(Parameter parameter) {
        return parameter.getType() == int.class;
    }

    @Override
    public Object tryConversion(String term, Parameter parameter) {
        try {
            return Integer.valueOf(term);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
