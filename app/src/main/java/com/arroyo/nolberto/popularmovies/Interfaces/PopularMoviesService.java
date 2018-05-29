package com.arroyo.nolberto.popularmovies.Interfaces;


import com.arroyo.nolberto.popularmovies.BuildConfig;
import com.arroyo.nolberto.popularmovies.Constants;
import com.arroyo.nolberto.popularmovies.Model.Response;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PopularMoviesService {
    //retrofit interface to make api calls


    @GET(Constants.GET_POPULAR_MOVIES+ BuildConfig.MOVIE_DB_API_KEY)
    Call<Response> getPopularMovies();

    @GET(Constants.GET_TOP_RATED_MOVIES+ BuildConfig.MOVIE_DB_API_KEY)
    Call<Response> getTopRatedMovies();
}
