package com.notjustmakers.galaxyboard.model;

public class Pixel {

    private int position;
    private Color color;

    public Pixel(int position) {
        this(position, new Color(0, 0, 0));
    }

    public Pixel(int position, Color color) {
        this.position = position;
        this.color = color;
    }

    public int getPosition() {
        return position;
    }

    public Color getColor() {
        return color;
    }
}

