package com.notjustmakers.galaxyboard.model;

import java.util.Arrays;
import java.util.List;

public class Color {

    public static final Color BLACK = new Color("#000000");
    public static final Color RED = new Color("#cf4647");
    public static final Color ORANGE = new Color("#fb6900");
    public static final Color CYAN = new Color("#00b9bd");
    public static final Color YELLOW = new Color("#f9d423");

    public static final List<Color> ALL = Arrays.asList(
        BLACK,
        RED,
        ORANGE,
        CYAN,
        YELLOW
    );

    private final int red;
    private final int green;
    private final int blue;
    private final String hex;

    public Color(long color) {
        this((short) (color >> 16) & 255, (short) (color >> 8) & 255, (short) (color) & 255);
    }

    public Color(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.hex = String.format("#%02x%02x%02x", red, green, blue);
    }

    public Color(String hex) {
        this.hex = hex;
        this.red = Integer.valueOf(hex.substring(1, 3), 16);
        this.green = Integer.valueOf(hex.substring(3, 5), 16);
        this.blue = Integer.valueOf(hex.substring(5, 7), 16);
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
        return hex;
    }
}
