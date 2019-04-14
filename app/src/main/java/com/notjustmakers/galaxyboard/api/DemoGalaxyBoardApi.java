package com.notjustmakers.galaxyboard.api;

import com.notjustmakers.galaxyboard.model.Board;
import com.notjustmakers.galaxyboard.model.ClimbingHold;
import com.notjustmakers.galaxyboard.model.Color;
import com.notjustmakers.galaxyboard.model.Pixel;
import com.notjustmakers.galaxyboard.model.Problem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DemoGalaxyBoardApi implements GalaxyBoardApi {

    private static final Integer N_ROWS = 10;
    private static final Integer N_COLUMNS = 5;

    private List<Problem> problems;
    private Board board;

    public DemoGalaxyBoardApi() {
        board = createRandomBoard();
        problems = new ArrayList<>();
        for (int i = 0; i < N_ROWS * N_COLUMNS; i++) {
            problems.add(createRandomProblem(i));
        }
    }

    @Override
    public Call<Status> setPixels(List<Pixel> pixels) {
        return new ValueCall<>(Status.OK);
    }

    @Override
    public Call<Status> setPixel(Color color, int ledPosition) {
        return new ValueCall<>(Status.OK);
    }

    @Override
    public Call<Problem> getProblem(int problemId) {
        return new ValueCall<>(problems.get(problemId));
    }

    @Override
    public Call<List<Problem>> getProblems() {
        return new ValueCall<>(problems);
    }

    @Override
    public Call<Board> getBoard() {
        return new ValueCall<>(board);
    }

    private Board createRandomBoard() {
        ClimbingHold[] climbingHolds = new ClimbingHold[N_ROWS * N_COLUMNS];
        for (int i = 0; i < N_ROWS * N_COLUMNS; i++) {
            int type = new Random().nextInt(5);
            climbingHolds[i] = new ClimbingHold(i, Color.BLACK, type);
        }
        return new Board(N_ROWS, N_COLUMNS, climbingHolds);
    }

    private Problem createRandomProblem(int problemId) {
        ClimbingHold[] climbingHolds = new ClimbingHold[board.getClimbingHolds().length];
        for (int i=0; i<climbingHolds.length; i++) {
            climbingHolds[i] = (ClimbingHold) board.getClimbingHolds()[i].clone();
            climbingHolds[i].setColor(Color.ALL.get(new Random().nextInt(Color.ALL.size())));
        }
        return new Problem(problemId, N_ROWS, N_COLUMNS, "My Problem " + problemId, getRandomDifficulty(), climbingHolds);
    }

    private String getRandomDifficulty() {
        int i = new Random().nextInt(12);
        switch (i) {
            case 0:
                return "6A";
            case 1:
                return "6A+";
            case 2:
                return "6B";
            case 3:
                return "6B+";
            case 4:
                return "6C";
            case 5:
                return "6C+";
            case 6:
                return "7A";
            case 7:
                return "7A+";
            case 8:
                return "7B";
            case 9:
                return "7B+";
            case 10:
                return "7C";
            default:
                return "7C+";
        }
    }

    private class ValueCall<T> implements Call<T> {

        private T value;

        public ValueCall(T value) {
            this.value = value;
        }

        @Override
        public Response<T> execute() {
            return Response.success(value);
        }

        @Override
        public void enqueue(Callback<T> callback) {
            callback.onResponse(this, Response.success(value));
        }

        @Override
        public boolean isExecuted() {
            return true;
        }

        @Override
        public void cancel() {

        }

        @Override
        public boolean isCanceled() {
            return false;
        }

        @Override
        public Call<T> clone() {
            return new ValueCall<>(value);
        }

        @Override
        public Request request() {
            return null;
        }
    }
}
