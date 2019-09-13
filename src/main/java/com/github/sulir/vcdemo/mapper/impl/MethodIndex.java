package com.github.sulir.vcdemo.mapper.impl;

import com.github.sulir.vcdemo.mapper.api.VoiceControllable;
import com.github.sulir.vcdemo.mapper.exceptions.UnsupportedParameterTypeException;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class MethodIndex {
    private List<ControlledMethod> methods = new ArrayList<>();

    public void addObject(Object object) {
        Class clazz = object.getClass();

        for (java.lang.reflect.Method method : clazz.getDeclaredMethods()) {
            if (shouldBeIndexed(method))
                addMethod(method, object);
        }
    }

    public void addMethod(Method method, Object object) {
        try {
            ControlledMethod controlledMethod = new ControlledMethod(object, method);
            controlledMethod.buildIndex();
            methods.add(controlledMethod);
        } catch (UnsupportedParameterTypeException e) {
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
