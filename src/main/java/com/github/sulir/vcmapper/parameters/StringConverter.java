package com.github.sulir.vcmapper.parameters;

import com.github.sulir.vcmapper.api.ValidValues;
import com.github.sulir.vcmapper.api.ValueSet;

import java.lang.reflect.Parameter;

public class StringConverter implements ParameterConverter {
    @Override
    public boolean isForParameter(Parameter parameter) {
        return parameter.getType().equals(String.class) && parameter.isAnnotationPresent(ValidValues.class);
    }

    @Override
    public Object tryConversion(String term, Parameter parameter) {
        try {
            ValidValues annotation = parameter.getAnnotation(ValidValues.class);
            ValueSet generator = annotation.value().getDeclaredConstructor().newInstance();

            if (generator.getValues().contains(term))
                return term;
            else
                return null;
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
            return null;
        }
    }
}
