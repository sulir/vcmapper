package com.github.sulir.vcmapper.api;

public @interface OnException {
    Class<?> of();
    String say();
}
