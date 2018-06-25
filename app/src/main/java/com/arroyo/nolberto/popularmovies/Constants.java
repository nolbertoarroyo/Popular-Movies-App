package com.arroyo.nolberto.popularmovies;

public class Constants {

    //MainActivity
    public static final String MOVIE_BASE_URL = "https://api.themoviedb.org/";
    public static final String POPULAR_MOVIES_SETTING = "popular movies";
    public static final String TOP_RATED_MOVIES_SETTING = "top-rated movies";
    public static final String MOVIES_SELECTED_KEY = "MovieSelected";

    //MovieDetailActivity

    public static final String MOVIE_BACKDROP_BASE_URL = "http://image.tmdb.org/t/p/w500";
    public static final String MOVIE_POSTER_BASE_URL = "http://image.tmdb.org/t/p/w185";
    public static final String VIDEO_YOUTUBE_APP_BASE_URL = "vnd.youtube:";
    public static final String VIDEO_YOUTUBE_BROWSER_BASE_URL = "http://www.youtube.com/watch?v=";

    //retrofit GET
    public static final String GET_POPULAR_MOVIES = "3/movie/popular?api_key=";
    public static final String GET_TOP_RATED_MOVIES = "3/movie/top_rated?api_key=";
    public static final String GET_MOVIE_VIDEO = "3/movie/{movie_id}/videos?api_key=";
    public static final String GET_MOVIE_REVIEWS = "3/movie/{movie_id}/reviews?api_key=";


}
