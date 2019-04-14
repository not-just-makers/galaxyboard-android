package com.notjustmakers.galaxyboard.model;

public class Board {

    private int rows;
    private int columns;
    private ClimbingHold[] climbingHolds;

    public Board(int rows, int columns, ClimbingHold[] climbingHolds) {
        this.rows = rows;
        this.columns = columns;
        this.climbingHolds = climbingHolds;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public ClimbingHold[] getClimbingHolds() {
        return climbingHolds;
    }

    public void setClimbingHolds(ClimbingHold[] climbingHolds) {
        this.climbingHolds = climbingHolds;
    }
}
