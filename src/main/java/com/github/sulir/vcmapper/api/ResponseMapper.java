package com.github.sulir.vcmapper.api;

public interface ResponseMapper<T> {
    String getResponse(T object);
}
