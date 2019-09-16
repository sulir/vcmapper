package com.github.sulir.vcmapper.sample;

import com.github.sulir.vcmapper.api.StringMapper;

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
