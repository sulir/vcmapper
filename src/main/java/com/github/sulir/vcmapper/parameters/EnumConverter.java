package com.github.sulir.vcmapper.parameters;

import com.github.sulir.vcmapper.api.Synonym;
import com.github.sulir.vcmapper.impl.Lexer;

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

            if (constantTerms.size() > 1)
                throw new UnsupportedOperationException("Multi-word enum constants not yet supported");

            if (term.equals(constantTerms.get(0)))
                return constant;

            for (Synonym synonym : parameter.getType().getAnnotationsByType(Synonym.class)) {
                if (constantTerms.get(0).equals(synonym.of()) && term.equals(synonym.is()))
                    return constant;
            }
        }

        return null;
    }
}
