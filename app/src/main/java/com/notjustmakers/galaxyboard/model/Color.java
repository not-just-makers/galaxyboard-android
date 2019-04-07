package com.notjustmakers.galaxyboard.model;

public class Color {

    private final int red;
    private final int green;
    private final int blue;

    public Color(long color) {
        this.red = (short) (color >> 16) & 255;
        this.green = (short) (color >> 8) & 255;
        this.blue = (short) (color) & 255;
    }

    public Color( int red, int green, int blue ) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }
}
