package com.github.sulir.vcmapper.exceptions;

public class NoMatchException extends Exception {
    public NoMatchException() {
        super("no method matches the sentence");
    }
}
