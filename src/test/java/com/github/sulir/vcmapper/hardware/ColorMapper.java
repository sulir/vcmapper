package com.github.sulir.vcmapper.hardware;

import com.github.sulir.vcmapper.base.Mapper;

import java.util.Map;

public class ColorMapper implements Mapper<Color> {

    @Override
    public Map<String, Color> getMap() {
        return Map.of(
                "white", new Color(255, 255, 255),
                "yellow", new Color(255, 255, 0)
        );
    }
}
