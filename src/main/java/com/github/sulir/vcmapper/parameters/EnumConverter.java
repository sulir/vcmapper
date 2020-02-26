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
                String synonymOf = Lexer.getInstance().tokenizeAndJoin(synonym.of());
                String synonymIs = Lexer.getInstance().tokenizeAndJoin(synonym.is());

                if ((constantName.equals(synonymOf) && term.equals(synonymIs)) ||
                        (constantName.equals(synonymIs) && term.equals(synonymOf)))
                    return constant;
            }
        }

        return null;
    }
}
