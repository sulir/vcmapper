package com.github.sulir.vcdemo.mapper.parameters;

public class NumberConverter implements ParameterConverter {
    @Override
    public boolean isForType(Class type) {
        return type == int.class;
    }

    @Override
    public Object tryConversion(String term, Class type) {
        try {
            return Integer.valueOf(term);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
