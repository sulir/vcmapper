package com.github.sulir.vcdemo.mapper.parameters;

import com.github.sulir.vcdemo.mapper.impl.Lexer;

import java.util.List;

public class EnumConverter implements ParameterConverter {
    @Override
    public boolean isForType(Class type) {
        return type.isEnum();
    }

    @Override
    public Object tryConversion(String term, Class type) {
        for (Object constant : type.getEnumConstants()) {
            List<String> constantTerms = Lexer.getInstance().tokenize(constant.toString());
            if (List.of(term).equals(constantTerms))
                return constant;
        }

        return null;
    }
}
