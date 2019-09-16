package com.github.sulir.vcdemo.mapper.exceptions;

import com.github.sulir.vcdemo.mapper.impl.Command;

import java.util.List;

public class AmbiguityException extends Exception {
    public AmbiguityException(List<Command> matchingMethods) {
        super("ambiguity: " + matchingMethods);
    }
}
