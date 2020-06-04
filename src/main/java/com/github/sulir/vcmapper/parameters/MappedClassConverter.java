package com.github.sulir.vcmapper.parameters;

import com.github.sulir.vcmapper.base.Mapper;
import com.github.sulir.vcmapper.base.Mapping;

import java.lang.reflect.Parameter;

public class MappedClassConverter implements ParameterConverter {
    @Override
    public boolean isForParameter(Parameter parameter) {
        return parameter.isAnnotationPresent(Mapping.class);
    }

    @Override
    public Object tryConversion(String term, Parameter parameter) {
        try {
            Mapping mapping = parameter.getAnnotation(Mapping.class);
            Mapper<?> mapper = mapping.value().getDeclaredConstructor().newInstance();
            return mapper.getMap().get(term);
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
            return null;
        }
    }
}
