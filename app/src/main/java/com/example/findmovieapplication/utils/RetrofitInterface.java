package com.example.findmovieapplication.utils;

import com.example.findmovieapplication.response.MovieSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitInterface {

    @GET("/3/search/movie")
    Call<MovieSearchResponse> searchedMovies(
            @Query("api_key") String key,
            @Query("query") String query,
            @Query("page") int pageNumber
    );
}
