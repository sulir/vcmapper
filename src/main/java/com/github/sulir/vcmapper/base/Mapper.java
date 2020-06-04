package com.github.sulir.vcmapper.base;

import java.util.Map;

public interface Mapper<T> {
    Map<String, T> getMap();
}
