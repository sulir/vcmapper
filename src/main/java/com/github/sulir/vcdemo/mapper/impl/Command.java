package com.github.sulir.vcdemo.mapper.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Command {
    private Object object;
    private Method method;
    private List<String> methodWords;
    private List<String> sentenceWords;
    private List<Object> parameterValues = new ArrayList<>();

    public Command(Object object, Method method, List<String> methodWords) {
        this.object = object;
        this.method = method;
        this.methodWords = methodWords;
    }

    public void setSentenceWords(List<String> sentenceWords) {
        this.sentenceWords = sentenceWords;
    }

    public void addParameterValue(Object value) {
        parameterValues.add(value);
    }

    public double calculateScore() {
        Set<String> methodSet = new HashSet<>(methodWords);
        Set<String> sentenceSet = new HashSet<>(sentenceWords);
        return SetUtils.jaccardIndex(methodSet, sentenceSet);
    }

    public void execute() {
        try {
            method.invoke(object, parameterValues.toArray());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return method.toString();
    }
}
