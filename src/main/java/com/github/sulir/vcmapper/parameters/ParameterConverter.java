package com.github.sulir.vcmapper.parameters;

import java.lang.reflect.Parameter;

public interface ParameterConverter {
    boolean isForParameter(Parameter parameter);
    Object tryConversion(String term, Parameter parameter);
}
