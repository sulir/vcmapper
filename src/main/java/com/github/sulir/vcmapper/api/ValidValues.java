package com.github.sulir.vcmapper.api;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ValidValues {
    Class<? extends ValueSet> value();
}
