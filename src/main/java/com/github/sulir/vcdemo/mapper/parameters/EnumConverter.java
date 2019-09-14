package com.github.sulir.vcdemo.mapper.parameters;

import com.github.sulir.vcdemo.mapper.impl.Lexer;

import java.lang.reflect.Parameter;
import java.util.List;

public class EnumConverter implements ParameterConverter {
    @Override
    public boolean isForParameter(Parameter parameter) {
        return parameter.getType().isEnum();
    }

    @Override
    public Object tryConversion(String term, Parameter parameter) {
        for (Object constant : parameter.getType().getEnumConstants()) {
            List<String> constantTerms = Lexer.getInstance().tokenize(constant.toString());
            if (List.of(term).equals(constantTerms))
                return constant;
        }

        return null;
    }
}
