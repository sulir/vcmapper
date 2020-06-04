package com.github.sulir.vcmapper.parameters;

import com.github.sulir.vcmapper.base.StringEnumeration;
import com.github.sulir.vcmapper.base.StringEnumerator;
import com.github.sulir.vcmapper.impl.Lexer;

import java.lang.reflect.Parameter;

public class StringConverter implements ParameterConverter {
    @Override
    public boolean isForParameter(Parameter parameter) {
        return parameter.getType().equals(String.class) && parameter.isAnnotationPresent(StringEnumeration.class);
    }

    @Override
    public Object tryConversion(String term, Parameter parameter) {
        try {
            StringEnumeration annotation = parameter.getAnnotation(StringEnumeration.class);
            StringEnumerator generator = annotation.value().getDeclaredConstructor().newInstance();
            Lexer lexer = Lexer.getInstance();

            for (String possibleValue : generator.getValidValues()) {
                if (term.equals(lexer.tokenizeAndJoin(possibleValue)))
                    return possibleValue;
            }

            return null;
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
            return null;
        }
    }
}
