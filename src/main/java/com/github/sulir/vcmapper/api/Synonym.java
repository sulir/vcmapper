package com.github.sulir.vcmapper.api;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Repeatable(Synonyms.class)
public @interface Synonym {
    String of();
    String is();
}
