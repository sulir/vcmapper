package com.github.sulir.vcdemo.sample;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Color color = (Color) o;
        return red == color.red &&
                green == color.green &&
                blue == color.blue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(red, green, blue);
    }

    @Override
    public String toString() {
        return String.format("Color(%d, %d, %d)", red, green, blue);
    }
}
