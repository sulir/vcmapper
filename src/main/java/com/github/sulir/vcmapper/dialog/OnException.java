package com.github.sulir.vcmapper.dialog;

public @interface OnException {
    Class<? extends Exception> of();
    String say();
}
