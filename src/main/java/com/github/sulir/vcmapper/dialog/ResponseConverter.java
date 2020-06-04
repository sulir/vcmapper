package com.github.sulir.vcmapper.dialog;

public interface ResponseConverter<T> {
    String toResponse(T object);
}
