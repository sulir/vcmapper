package com.github.sulir.vcmapper.impl;

import com.github.sulir.vcmapper.base.CommandExecutor;
import com.github.sulir.vcmapper.base.VoiceCommand;
import com.github.sulir.vcmapper.exceptions.AmbiguityException;
import com.github.sulir.vcmapper.exceptions.NoMatchException;
import com.github.sulir.vcmapper.exceptions.UnsupportedParameterException;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
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

    public Command tryRegex(String sentence, CommandExecutor executor) {
        VoiceCommand annotation = method.getAnnotation(VoiceCommand.class);

        if (annotation != null) {
            String regex = annotation.value();
            Matcher matcher = Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(sentence);

            if (matcher.matches()) {
                Command command = new Command(object, method, Collections.emptyList());

                for (int i = 1; i <= method.getParameterCount(); i++) {
                    String group = matcher.group(i);

                    if (method.getParameters()[i - 1].getType() == Runnable.class) {
                        command.addParameterValue((Runnable) () -> {
                            try {
                                executor.execute(group);
                            } catch (NoMatchException | AmbiguityException e) {
                                e.printStackTrace();
                            }
                        });
                    } else {
                        command.addParameterValue(group);
                    }
                }

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

            while (iterator.hasNext()) { // multi-word parameters not yet supported
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
