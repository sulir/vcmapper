package com.github.sulir.vcdemo.mapper.exceptions;

import com.github.sulir.vcdemo.mapper.impl.Command;

import java.util.List;

public class AmbiguityException extends Exception {
    private List<Command> matchingCommands;

    public AmbiguityException(List<Command> matchingCommands) {
        super(matchingCommands.toString());
        this.matchingCommands = matchingCommands;
    }

    public List<Command> getMatchingCommands() {
        return matchingCommands;
    }
}
