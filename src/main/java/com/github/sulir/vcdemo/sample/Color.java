package com.github.sulir.vcdemo.sample;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Color {
    private int red;
    private int green;
    private int blue;

    // a) string constructor
    public Color(String name) {

    }

    // b) separate mapping class (see ColorMapper)
    public Color(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }
}
