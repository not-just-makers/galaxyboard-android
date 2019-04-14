package com.notjustmakers.galaxyboard.model;

import java.util.Arrays;

/**
 * Climbing Wall Matrix.
 * It represents a matrix of climbing holds from a climbing wall.
 *
 * @author Andres Sanchez (andressanchez)
 */
public class ClimbingWallMatrix {

    private int rows;
    private int columns;
    private ClimbingHold[][] climbingHolds;

    public ClimbingWallMatrix(int rows, int columns, ClimbingHold[] climbingHolds) {
        this.rows = rows;
        this.columns = columns;
        this.climbingHolds = new ClimbingHold[rows][columns];

        // Make sure all the climbingHolds are sorted
        Arrays.sort(climbingHolds);

        // Set the correct position for our climbing holds
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                this.climbingHolds[i][j] = climbingHolds[j % 2 == 0 ? ((rows - i - 1) + j * rows) : (i + j * rows)];
            }
        }

    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public ClimbingHold[][] getClimbingHolds() {
        return climbingHolds;
    }
}
