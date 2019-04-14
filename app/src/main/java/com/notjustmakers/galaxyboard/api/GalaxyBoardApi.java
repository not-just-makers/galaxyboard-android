package com.notjustmakers.galaxyboard.api;

import com.notjustmakers.galaxyboard.model.Color;
import com.notjustmakers.galaxyboard.model.Pixel;
import com.notjustmakers.galaxyboard.model.Problem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface GalaxyBoardApi {

    @POST("v1/led")
    Call<Status> setPixels(@Body List<Pixel> pixels);

    @POST("v1/led/{ledPosition}")
    Call<Status> setPixel(@Body Color color, @Path("ledPosition") int ledPosition);

    @GET("v1/problem/{problemId}")
    Call<Problem> getProblem(@Path("problemId") int problemId);
}
