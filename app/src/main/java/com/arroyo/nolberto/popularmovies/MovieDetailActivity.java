package com.arroyo.nolberto.popularmovies;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.arroyo.nolberto.popularmovies.Model.Response;
import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {
    private ImageView moviePoster;
    private TextView overViewTv, movieTitleTv, movieReleaseDateTv, movieRatingTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        ActionBar actionBar = getSupportActionBar();

        // Enable the Up button
        actionBar.setDisplayHomeAsUpEnabled(true);

        moviePoster = (ImageView) findViewById(R.id.detail_movie_thumbnail_iv);
        overViewTv = (TextView) findViewById(R.id.movie_description_tv);
        movieTitleTv = (TextView) findViewById(R.id.detail_movie_title_tv);
        movieReleaseDateTv = (TextView) findViewById(R.id.movie_release_date_tv);
        movieRatingTv = (TextView) findViewById(R.id.movie_rating_tv);

        Intent receivedIntent = getIntent();
        Response.MoviesModel movie = receivedIntent.getParcelableExtra("MovieSelected");

        overViewTv.setText(movie.getOverview());
        movieTitleTv.setText(movie.getOriginal_title());
        String pic = getApplicationContext().getString(R.string.movie_image_base_url) + movie.getBackdrop_path();
        movieReleaseDateTv.setText(movie.getRelease_date());
        double rating = movie.getVote_average();
        movieRatingTv.setText(String.valueOf(rating));

        Picasso.with(this).load(pic).into(moviePoster);

    }
}
