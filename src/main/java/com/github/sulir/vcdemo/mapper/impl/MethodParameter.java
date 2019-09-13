package com.github.sulir.vcdemo.mapper.impl;

public class MethodParameter {
    private Class type;

    public MethodParameter(Class type) {
        this.type = type;
    }

    public Object match(String term) {
        if (type == int.class) {
            try {
                return Integer.valueOf(term);
            } catch (NumberFormatException e) {
                return null;
            }
        } else {
            return null;
        }
    }
}
