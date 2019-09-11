package com.github.sulir.vcdemo.mapper.impl;

import com.github.sulir.vcdemo.mapper.annotations.VoiceControllable;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

public class CommandIndex {
    private List<Command> commands = new ArrayList<>();

    public void addObject(Object object) {
        Class clazz = object.getClass();

        for (Method method : clazz.getDeclaredMethods()) {
            if (shouldBeIndexed(method))
                addMethod(method, object);
        }
    }

    public void addMethod(Method method, Object object) {
        Command command = new Command(object, method);

        List<Term> methodTerms = Lexer.getInstance().tokenize(method.getName());
        command.addTerms(methodTerms);

        for (Parameter parameter : method.getParameters()) {
            // we need to run javac with the "-parameters" argument to preserve argument names
            command.addTerms(Lexer.getInstance().tokenize(parameter.getName()));
        }

        String className = object.getClass().getSimpleName();
        String domainClassName = className.split("\\$")[0].split("Service$")[0];
        List<Term> classWords = Lexer.getInstance().tokenize(domainClassName);
        command.addTerms(classWords);

        commands.add(command);
    }

    public List<Command> getCommands() {
        return commands;
    }

    private boolean shouldBeIndexed(Method method) {
        if (!Modifier.isPublic(method.getModifiers()))
            return false;

        if (!method.getDeclaringClass().isAnnotationPresent(VoiceControllable.class)
                && !method.isAnnotationPresent(VoiceControllable.class))
            return false;

        if (method.getName().contains("Mockito"))
            return false;

        try {
            Object.class.getDeclaredMethod(method.getName(), method.getParameterTypes());
            return false;
        } catch (NoSuchMethodException e) {
            return true;
        }
    }
}
