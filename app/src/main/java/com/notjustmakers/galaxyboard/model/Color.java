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

    public Color(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public static Color fromHex(String hex) {
        return new Color(
            Integer.valueOf(hex.substring(1, 3), 16),
            Integer.valueOf(hex.substring(3, 5), 16),
            Integer.valueOf(hex.substring(5, 7), 16));
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

    public String getHex() {
        return String.format("#%02x%02x%02x", red, green, blue);
    }
}
