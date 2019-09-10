package com.github.sulir.vcdemo.mapper.annotations;

import java.util.Map;

public interface StringMapper<T> {
    Map<String, T> getMap();
}
