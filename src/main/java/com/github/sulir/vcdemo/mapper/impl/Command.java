package com.github.sulir.vcdemo.mapper.impl;

import com.github.sulir.vcdemo.mapper.api.Synonym;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

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

        double maxScore = SetUtils.jaccardIndex(methodSet, sentenceSet);

        for (Synonym synonym: method.getAnnotationsByType(Synonym.class)) {
            Set<String> methodWithSynonyms = methodSet.stream().map((word) -> {
                if (word.equals(synonym.is()))
                    return synonym.of();
                else
                    return word;
            }).collect(Collectors.toSet());

            double score = SetUtils.jaccardIndex(methodWithSynonyms, sentenceSet);
            if (score > maxScore)
                maxScore = score;
        }

        return maxScore;
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
