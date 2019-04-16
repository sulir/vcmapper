package com.github.sulir.vcdemo.sample;

import com.github.sulir.vcdemo.mapper.StringMapper;

import java.util.Map;

public class ColorMapper implements StringMapper<Color> {

    @Override
    public Map<String, Color> getMap() {
        return Map.of(
                "red", new Color(255, 255, 0),
                "green", new Color(0, 255, 0),
                "blue", new Color(0, 0, 255)
        );
    }
}
