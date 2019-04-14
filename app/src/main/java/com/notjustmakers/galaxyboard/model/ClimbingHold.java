package com.notjustmakers.galaxyboard.model;

/**
 * Climbing Wall Matrix.
 * It represents a matrix of climbing holds from a climbing wall.
 *
 * @author Andres Sanchez (andressanchez)
 */
public class ClimbingHold implements Comparable {

    private int position;
    private Color color;
    private int type;

    public ClimbingHold(int position, Color color, int type) {
        this.position = position;
        this.color = color;
        this.type = type;
    }

    public int getPosition() {
        return position;
    }

    public Color getColor() {
        return color;
    }

    public int getType() {
        return type;
    }

    @Override
    public int compareTo(Object o) {
        ClimbingHold c = (ClimbingHold) o;
        return Integer.compare(position, c.getPosition());
    }
}
