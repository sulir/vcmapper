package com.github.sulir.vcdemo.mapper.exceptions;

import com.github.sulir.vcdemo.mapper.impl.Command;

import java.util.List;

public class AmbiguityException extends Exception {
    private List<Command> matchingMethods;

    public AmbiguityException(List<Command> matchingMethods) {
        super(matchingMethods.toString());
        this.matchingMethods = matchingMethods;
    }

    public List<Command> getMatchingMethods() {
        return matchingMethods;
    }
}
