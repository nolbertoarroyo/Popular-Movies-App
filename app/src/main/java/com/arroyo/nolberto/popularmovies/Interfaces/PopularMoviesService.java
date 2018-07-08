package com.arroyo.nolberto.popularmovies.Interfaces;


import com.arroyo.nolberto.popularmovies.BuildConfig;
import com.arroyo.nolberto.popularmovies.Utils.Constants;
import com.arroyo.nolberto.popularmovies.Model.Response;
import com.arroyo.nolberto.popularmovies.Model.VideoResults;
import com.arroyo.nolberto.popularmovies.Model.VideoReviews;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PopularMoviesService {
    //retrofit interface to make api calls


    @GET(Constants.GET_POPULAR_MOVIES+ BuildConfig.MOVIE_DB_API_KEY)
    Call<Response> getPopularMovies();

    @GET(Constants.GET_TOP_RATED_MOVIES+ BuildConfig.MOVIE_DB_API_KEY)
    Call<Response> getTopRatedMovies();

    @GET(Constants.GET_MOVIE_VIDEO+ BuildConfig.MOVIE_DB_API_KEY)
    Call<VideoResults> getVideoResults(@Path("movie_id") String movieId);

    @GET(Constants.GET_MOVIE_REVIEWS+ BuildConfig.MOVIE_DB_API_KEY)
    Call<VideoReviews> getVideoReviews(@Path("movie_id") String movieId);


}
