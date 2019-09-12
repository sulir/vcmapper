package com.github.sulir.vcdemo.mapper.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Command {
    private Object object;
    private Method method;
    private List<Term> terms = new ArrayList<>();

    public Command(Object object, Method method) {
        this.object = object;
        this.method = method;
    }

    public void addTerms(List<Term> terms) {
        this.terms.addAll(terms);
    }

    public double calculateScore(String sentence) {
        List<Term> sentenceTerms = Lexer.getInstance().tokenize(sentence);

        Set<Term> commandSet = new HashSet<>(terms);
        Set<Term> sentenceSet = new HashSet<>(sentenceTerms);
        return jaccardIndex(commandSet, sentenceSet);
    }

    public void execute() {
        try {
            method.invoke(object);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return terms.stream().map(Term::toString).collect(Collectors.joining(" "));
    }

    private double jaccardIndex(Set<Term> set1, Set<Term> set2) {
        int set1size = set1.size();
        int set2size = set2.size();

        set1.retainAll(set2);
        int intersectionSize = set1.size();

        return ((double) intersectionSize) / (set1size + set2size - intersectionSize);
    }
}