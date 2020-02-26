package com.github.sulir.vcmapper.parameters;

import com.github.sulir.vcmapper.api.Synonym;
import com.github.sulir.vcmapper.impl.Lexer;

import java.lang.reflect.Parameter;

public class EnumConverter implements ParameterConverter {
    @Override
    public boolean isForParameter(Parameter parameter) {
        return parameter.getType().isEnum();
    }

    @Override
    public Object tryConversion(String term, Parameter parameter) {
        for (Object constant : parameter.getType().getEnumConstants()) {
            String constantName = Lexer.getInstance().tokenizeAndJoin(constant.toString());

            if (term.equals(constantName))
                return constant;

            for (Synonym synonym : parameter.getType().getAnnotationsByType(Synonym.class)) {
                if ((constantName.equals(synonym.of()) && term.equals(synonym.is())) ||
                        (constantName.equals(synonym.is()) && term.equals(synonym.of())))
                    return constant;
            }
        }

        return null;
    }
}
