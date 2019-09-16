package com.github.sulir.vcmapper.exceptions;

import java.lang.reflect.Parameter;

public class UnsupportedParameterException extends Exception {
    public UnsupportedParameterException(Parameter parameter) {
        super("unsupported parameter: " + parameter);
    }
}
