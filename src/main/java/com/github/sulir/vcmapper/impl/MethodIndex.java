package com.github.sulir.vcmapper.impl;

import com.github.sulir.vcmapper.base.VoiceCommand;
import com.github.sulir.vcmapper.base.VoiceControllable;
import com.github.sulir.vcmapper.exceptions.UnsupportedParameterException;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class MethodIndex {
    private final List<ControlledMethod> methods = new ArrayList<>();

    public void addObject(Object object) {
        Class<?> clazz = object.getClass();

        for (java.lang.reflect.Method method : clazz.getDeclaredMethods()) {
            if (shouldBeIndexed(method))
                addMethod(method, object);
        }
    }

    public void addMethod(Method method, Object object) {
        try {
            ControlledMethod controlledMethod = new ControlledMethod(object, method);

            if (method.getDeclaringClass().isAnnotationPresent(VoiceControllable.class)
                    || method.isAnnotationPresent(VoiceControllable.class)) {
                controlledMethod.buildIndex(); // if a method does not have only a fallback annotation
            }

            methods.add(controlledMethod);
        } catch (UnsupportedParameterException e) {
            System.err.println(e.getMessage());
        }
    }

    public List<ControlledMethod> getMethods() {
        return methods;
    }

    private boolean shouldBeIndexed(java.lang.reflect.Method method) {
        if (!Modifier.isPublic(method.getModifiers()))
            return false;

        if (!method.getDeclaringClass().isAnnotationPresent(VoiceControllable.class)
                && !method.isAnnotationPresent(VoiceControllable.class)
                && !method.isAnnotationPresent(VoiceCommand.class))
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
