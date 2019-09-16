package com.github.sulir.vcdemo.mapper.exceptions;

public class NoMatchException extends Exception {
    public NoMatchException() {
        super("no method matches the sentence");
    }
}
