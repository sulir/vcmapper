package com.github.sulir.vcmapper.impl;

import com.github.sulir.vcmapper.api.Synonym;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
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
        Lexer lexer = Lexer.getInstance();

        for (Synonym synonym: findSynonyms()) {
            Set<String> methodWithSynonyms = new HashSet<>(methodSet);
            for (String word : methodWithSynonyms) {
                if (word.equals(lexer.tokenizeAndJoin(synonym.of()))) {
                    methodWithSynonyms.remove(word);
                    methodWithSynonyms.addAll(lexer.tokenize(synonym.is()));
                    break;
                } else if (word.equals(lexer.tokenizeAndJoin(synonym.is()))) {
                    methodWithSynonyms.remove(word);
                    methodWithSynonyms.addAll(lexer.tokenize(synonym.of()));
                    break;
                }
            }

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

    private List<Synonym> findSynonyms() {
        List<Synonym> result = new ArrayList<>();

        result.addAll(Arrays.asList(method.getAnnotationsByType(Synonym.class)));
        result.addAll(Arrays.asList(method.getDeclaringClass().getAnnotationsByType(Synonym.class)));

        return result;
    }
}
