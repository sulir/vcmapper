package com.github.sulir.vcdemo.mapper.impl;

import opennlp.tools.stemmer.PorterStemmer;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Lexer {
    private static final List<String> stopWords = List.of("a", "an", "the");
    private static Lexer instance = new Lexer();

    private PorterStemmer stemmer = new PorterStemmer();

    private Lexer() {}

    public static Lexer getInstance() {
        return instance;
    }

    public List<Term> tokenize(String string) {
        List<String> words = split(string);
        words.removeAll(stopWords);
        return words.stream().map(stemmer::stem).map(Term::new).collect(Collectors.toList());
    }

    private List<String> split(String string) {
        String[] splitIdentifiers = StringUtils.splitByCharacterTypeCamelCase(string);
        String joinedLower = String.join(" ", splitIdentifiers).toLowerCase();
        return new ArrayList<>(Arrays.asList(joinedLower.split("\\W+")));
    }
}
