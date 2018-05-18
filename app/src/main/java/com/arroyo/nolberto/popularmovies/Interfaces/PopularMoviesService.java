package com.arroyo.nolberto.popularmovies.Interfaces;


import com.arroyo.nolberto.popularmovies.BuildConfig;
import com.arroyo.nolberto.popularmovies.Model.Response;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PopularMoviesService {


    @GET("3/movie/popular?api_key="+ BuildConfig.MOVIE_DB_API_KEY)
    Call<Response> getPopularMovies();

    @GET("3/movie/top_rated?api_key="+ BuildConfig.MOVIE_DB_API_KEY)
    Call<Response> getTopRatedMovies();
}
