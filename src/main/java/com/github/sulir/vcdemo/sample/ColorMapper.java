package com.github.sulir.vcdemo.sample;

import com.github.sulir.vcdemo.mapper.api.StringMapper;

import java.util.Map;

public class ColorMapper implements StringMapper<Color> {

    @Override
    public Map<String, Color> getMap() {
        return Map.of(
                "white", new Color(255, 255, 255),
                "yellow", new Color(255, 255, 0)
        );
    }
}
