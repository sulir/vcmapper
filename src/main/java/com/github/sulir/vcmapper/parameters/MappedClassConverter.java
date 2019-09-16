package com.github.sulir.vcmapper.parameters;

import com.github.sulir.vcmapper.api.StringMapper;
import com.github.sulir.vcmapper.api.StringMapping;

import java.lang.reflect.Parameter;

public class MappedClassConverter implements ParameterConverter {
    @Override
    public boolean isForParameter(Parameter parameter) {
        return parameter.isAnnotationPresent(StringMapping.class);
    }

    @Override
    public Object tryConversion(String term, Parameter parameter) {
        try {
            StringMapping mapping = parameter.getAnnotation(StringMapping.class);
            StringMapper mapper = mapping.value().getDeclaredConstructor().newInstance();
            return mapper.getMap().get(term);
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
            return null;
        }
    }
}
