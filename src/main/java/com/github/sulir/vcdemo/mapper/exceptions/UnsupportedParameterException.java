package com.github.sulir.vcdemo.mapper.exceptions;

import java.lang.reflect.Parameter;

public class UnsupportedParameterException extends Exception {
    public UnsupportedParameterException(Parameter parameter) {
        super("unsupported parameter: " + parameter);
    }
}
