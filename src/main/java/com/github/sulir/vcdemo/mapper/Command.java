package com.github.sulir.vcdemo.mapper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Command {
    private Object object;
    private Method method;
    private List<String> words = new ArrayList<>();

    public Command(Object object, Method method) {
        this.object = object;
        this.method = method;
    }

    public void addWords(List<String> words) {
        this.words.addAll(words);
    }

    public boolean matches(String sentence) {
        List<String> sentenceWords = Lexer.getInstance().tokenize(sentence);

        return sentenceWords.equals(words);
    }

    public void execute() {
        try {
            method.invoke(object);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
