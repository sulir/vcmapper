package com.github.sulir.vcdemo.mapper.impl;

import java.util.Objects;

public class Term {
    public enum Type {
        WORD
    }

    private Type type;
    private String word;

    public Term(String word) {
        this.type = Type.WORD;
        this.word = word;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Term term = (Term) o;
        return type == term.type &&
                word.equals(term.word);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, word);
    }

    @Override
    public String toString() {
        return word;
    }
}
