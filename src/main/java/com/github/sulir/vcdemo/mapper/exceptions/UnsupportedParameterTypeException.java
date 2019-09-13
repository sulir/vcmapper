package com.github.sulir.vcdemo.mapper.exceptions;

public class UnsupportedParameterTypeException extends Exception {
    public UnsupportedParameterTypeException(Class type) {
        super("unsupported parameter type: " + type.getSimpleName());
    }
}
