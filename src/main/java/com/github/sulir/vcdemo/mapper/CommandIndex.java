package com.github.sulir.vcdemo.mapper;

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

        List<String> methodWords = Lexer.getInstance().tokenize(method.getName());
        command.addWords(methodWords);

        for (Parameter parameter : method.getParameters()) {
            // we need to run javac with the "-parameters" argument to preserve argument names
            command.addWords(Lexer.getInstance().tokenize(parameter.getName()));
        }

        String className = object.getClass().getSimpleName();
        String domainClassName = className.split("\\$")[0].split("Service$")[0];
        List<String> classWords = Lexer.getInstance().tokenize(domainClassName);
        command.addWords(classWords);

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
