package com.github.sulir.vcmapper.exceptions;

import com.github.sulir.vcmapper.impl.Command;

import java.util.List;

public class AmbiguityException extends Exception {
    public AmbiguityException(List<Command> matchingMethods) {
        super("ambiguity: " + matchingMethods);
    }
}
