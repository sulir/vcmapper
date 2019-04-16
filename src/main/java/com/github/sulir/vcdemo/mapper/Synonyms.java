package com.github.sulir.vcdemo.mapper;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Synonyms {
    Synonym[] value();
}
