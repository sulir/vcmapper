package com.github.sulir.vcdemo.mapper.annotations;

public @interface OnException {
    Class<?> of();
    String say();
}
