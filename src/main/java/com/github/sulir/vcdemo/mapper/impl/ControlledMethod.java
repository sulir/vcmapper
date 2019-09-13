package com.github.sulir.vcdemo.mapper.impl;

import com.github.sulir.vcdemo.mapper.exceptions.UnsupportedParameterTypeException;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

public class ControlledMethod {
    private Object object;
    private Method method;
    private List<String> words = new ArrayList<>();
    private List<MethodParameter> parameters = new ArrayList<>();

    public ControlledMethod(Object object, Method method) {
        this.object = object;
        this.method = method;
    }

    public void buildIndex() throws UnsupportedParameterTypeException {
        List<String> methodWords = Lexer.getInstance().tokenize(method.getName());
        words.addAll(methodWords);

        for (Parameter parameter : method.getParameters()) {
            // we need to run javac with the "-parameters" argument to preserve parameter names
            parameters.add(new MethodParameter(parameter.getType()));
        }

        String className = object.getClass().getSimpleName();
        String domainClassName = className.split("\\$")[0].split("Service$")[0];
        List<String> classWords = Lexer.getInstance().tokenize(domainClassName);
        words.addAll(classWords);
    }

    public Command matchParameters(List<String> sentenceWords) {
        sentenceWords = new ArrayList<>(sentenceWords);
        Command command = new Command(object, method, words);

        for (MethodParameter parameter : parameters) {
            Iterator<String> iterator = sentenceWords.iterator();
            boolean matchFound = false;

            while (iterator.hasNext()) {
                Object match = parameter.tryConversion(iterator.next());

                if (match != null) {
                    iterator.remove();
                    command.addParameterValue(match);
                    matchFound = true;
                    break;
                }
            }

            if (!matchFound)
                return null;
        }

        command.setSentenceWords(sentenceWords);
        return command;
    }
}
