package com.notjustmakers.galaxyboard.model;

public class Problem {

    private int id;
    private int rows;
    private int columns;
    private String name;
    private String difficulty;
    private ClimbingHold[] climbingHolds;

    public Problem(int id, int rows, int columns, String name, String difficulty, ClimbingHold[] climbingHolds) {
        this.id = id;
        this.rows = rows;
        this.columns = columns;
        this.name = name;
        this.difficulty = difficulty;
        this.climbingHolds = climbingHolds;
    }

    public int getId() {
        return id;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public String getName() {
        return name;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public ClimbingHold[] getClimbingHolds() {
        return climbingHolds;
    }
}
