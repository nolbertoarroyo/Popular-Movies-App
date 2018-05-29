package com.arroyo.nolberto.popularmovies.Activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.arroyo.nolberto.popularmovies.Constants;
import com.arroyo.nolberto.popularmovies.Model.Response;
import com.arroyo.nolberto.popularmovies.R;
import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {
    private ImageView moviePoster, movieBackdrop;
    private TextView overViewTv, movieTitleTv, movieReleaseDateTv, movieRatingTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        ActionBar actionBar = getSupportActionBar();

        // Enable the Up button
        if (actionBar != null){

            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        setViews();

        //receiving movie object from mainActivity and populating views with new data
        Intent receivedIntent = getIntent();
        Response.MoviesModel movie = receivedIntent.getParcelableExtra(Constants.MOVIES_SELECTED_KEY);

        overViewTv.setText(movie.getOverview());
        movieTitleTv.setText(movie.getOriginal_title());
        String pic = Constants.MOVIE_BACKDROP_BASE_URL + movie.getBackdrop_path();
        String moviePosterUrl = Constants.MOVIE_POSTER_BASE_URL + movie.getPoster_path();
        movieReleaseDateTv.setText(movie.getRelease_date());
        double rating = movie.getVote_average();
        movieRatingTv.setText(String.valueOf(rating));

        Picasso.with(this).load(pic).into(movieBackdrop);
        Picasso.with(this).load(moviePosterUrl).into(moviePoster);

    }
    void setViews(){
        moviePoster = (ImageView)findViewById(R.id.detail_movie_poster_iv);
        movieBackdrop = (ImageView) findViewById(R.id.detail_movie_thumbnail_iv);
        overViewTv = (TextView) findViewById(R.id.movie_description_tv);
        movieTitleTv = (TextView) findViewById(R.id.detail_movie_title_tv);
        movieReleaseDateTv = (TextView) findViewById(R.id.release_date_value_tv);
        movieRatingTv = (TextView) findViewById(R.id.movie_rating_value_tv);
    }

}
