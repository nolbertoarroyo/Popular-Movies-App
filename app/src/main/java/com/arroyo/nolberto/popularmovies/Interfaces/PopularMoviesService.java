package com.arroyo.nolberto.popularmovies.Interfaces;


import com.arroyo.nolberto.popularmovies.Model.Response;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PopularMoviesService {


    @GET("3/movie/popular?api_key=d863b41df31e4254a356c5a19309175e")
    Call<Response> getPopularMovies();
}
