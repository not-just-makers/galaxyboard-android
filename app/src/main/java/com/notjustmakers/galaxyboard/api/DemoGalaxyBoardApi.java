package com.notjustmakers.galaxyboard.api;

import com.notjustmakers.galaxyboard.model.ClimbingHold;
import com.notjustmakers.galaxyboard.model.Color;
import com.notjustmakers.galaxyboard.model.Pixel;
import com.notjustmakers.galaxyboard.model.Problem;

import java.util.List;
import java.util.Random;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DemoGalaxyBoardApi implements GalaxyBoardApi {

    private static final Integer N_ROWS = 10;
    private static final Integer N_COLUMNS = 5;

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
        ClimbingHold[] climbingHolds = new ClimbingHold[N_ROWS * N_COLUMNS];
        for (int i = 0; i < N_ROWS * N_COLUMNS; i++) {
            climbingHolds[i] = new ClimbingHold(i, new Color(0, 0, 0), new Random().nextInt(5));
        }
        return new ValueCall<>(new Problem(N_ROWS, N_COLUMNS, "My Problem " + problemId, "7A", climbingHolds));
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
