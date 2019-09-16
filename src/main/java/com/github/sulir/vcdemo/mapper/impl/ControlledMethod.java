package com.github.sulir.vcdemo.mapper.impl;

import com.github.sulir.vcdemo.mapper.api.VoiceCommand;
import com.github.sulir.vcdemo.mapper.exceptions.UnsupportedParameterException;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ControlledMethod {
    private Object object;
    private Method method;
    private List<String> words = new ArrayList<>();
    private List<MethodParameter> parameters = new ArrayList<>();

    public ControlledMethod(Object object, Method method) {
        this.object = object;
        this.method = method;
    }

    public void buildIndex() throws UnsupportedParameterException {
        List<String> methodWords = Lexer.getInstance().tokenize(method.getName());
        words.addAll(methodWords);

        for (Parameter parameter : method.getParameters()) {
            // we would need to run javac with "-parameters" if we wanted to preserve parameter names
            parameters.add(new MethodParameter(parameter));
        }

        String className = object.getClass().getSimpleName();
        String domainClassName = className.split("\\$")[0].split("Service$")[0];
        List<String> classWords = Lexer.getInstance().tokenize(domainClassName);
        words.addAll(classWords);
    }

    public Command tryRegex(String sentence) {
        VoiceCommand annotation = method.getAnnotation(VoiceCommand.class);

        if (annotation != null) {
            String regex = annotation.value();
            Matcher matcher = Pattern.compile(regex).matcher(sentence);

            if (matcher.matches()) {
                Command command = new Command(object, method, Collections.emptyList());

                for (int i = 1; i <= matcher.groupCount(); i++)
                    command.addParameterValue(matcher.group(i));

                return command;
            }
        }

        return null;
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
