package com.arroyo.nolberto.popularmovies.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arroyo.nolberto.popularmovies.Adapter.MovieReviewsAdapter;
import com.arroyo.nolberto.popularmovies.Adapter.TrailersRecyclerViewAdapter;
import com.arroyo.nolberto.popularmovies.Utils.AppExecutors;
import com.arroyo.nolberto.popularmovies.Utils.Constants;
import com.arroyo.nolberto.popularmovies.Interfaces.OnTrailerClickListener;
import com.arroyo.nolberto.popularmovies.Interfaces.PopularMoviesService;
import com.arroyo.nolberto.popularmovies.Model.Response;
import com.arroyo.nolberto.popularmovies.Model.VideoResults;
import com.arroyo.nolberto.popularmovies.Model.VideoReviews;
import com.arroyo.nolberto.popularmovies.R;
import com.arroyo.nolberto.popularmovies.Utils.FavoritesDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieDetailActivity extends AppCompatActivity implements OnTrailerClickListener{
    private Response.MoviesModel movie;
    private RecyclerView videoRecyclerView;
    private RecyclerView reviewsRecyclerView;
    private TrailersRecyclerViewAdapter videoRvAdapter;
    private RecyclerView.LayoutManager videoRvLayoutManager;
    private MovieReviewsAdapter rvAdapter;
    private RecyclerView.LayoutManager rvLayoutManager;
    private FavoritesDatabase favsDb;
    private SharedPreferences.Editor editor;
    private SharedPreferences preferences;
    private ArrayList<VideoReviews.Review>reviewsList;
    private ArrayList<VideoResults.VideoData> videosList;
    private ImageView moviePoster, movieBackdrop;
    private TextView overViewTv, movieTitleTv, movieReleaseDateTv, movieRatingTv;
    private Button favoritesButton;
    private Boolean favoriteExists = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        ActionBar actionBar = getSupportActionBar();

        // Enable the Up button
        if (actionBar != null){

            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        setSharedPreference();
        favsDb = FavoritesDatabase.getDbInstance(getApplicationContext());
        setViews();

        //receiving movie object from mainActivity and populating views with new data
        Intent receivedIntent = getIntent();
        movie = receivedIntent.getParcelableExtra(Constants.MOVIES_SELECTED_KEY);

        setMovieDetails();
        setDetailRecyclerView();
        getReviewsList();
        getVideoList();

        //adds or deletes favoriteMovie from favorites database, updates UI
        favoritesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!favoriteExists){

                    favoriteExists = true;
                    editor.putBoolean(Constants.FAVORITES_EXISTS_KEY+ movie.getId(),favoriteExists).apply();
                    insertFavorite();
                    favoritesButton.setText(R.string.remove_favorite);
                    favoritesButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    Toast.makeText(MovieDetailActivity.this, getString(R.string.added_favorite) + " " + movie.getTitle(), Toast.LENGTH_SHORT).show();

                }else{
                    deleteFavorite();
                    favoritesButton.setText(R.string.add_favorite);
                    favoritesButton.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    Toast.makeText(MovieDetailActivity.this, getString(R.string.removed_favorite) + " " + movie.getTitle(), Toast.LENGTH_SHORT).show();
                    favoriteExists = false;
                    editor.putBoolean(Constants.FAVORITES_EXISTS_KEY+ movie.getId(),favoriteExists).apply();
                }
            }
        });


    }

    private void setMovieDetails() {
        overViewTv.setText(movie.getOverview());
        movieTitleTv.setText(movie.getOriginal_title());
        String pic = Constants.MOVIE_BACKDROP_BASE_URL + movie.getBackdrop_path();
        final String moviePosterUrl = Constants.MOVIE_POSTER_BASE_URL + movie.getPoster_path();
        movieReleaseDateTv.setText(movie.getRelease_date());
        double rating = movie.getVote_average();
        movieRatingTv.setText(String.valueOf(rating));

        Picasso.with(this).load(pic).into(movieBackdrop);
        Picasso.with(this).load(moviePosterUrl).into(moviePoster);
    }

    @Override
    protected void onResume() {
        this.favoriteExists = preferences.getBoolean(Constants.FAVORITES_EXISTS_KEY+ movie.getId(), false);
        if (favoriteExists){
            favoritesButton.setText(R.string.remove_favorite);
            favoritesButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }else{
            favoritesButton.setText(R.string.add_favorite);
            favoritesButton.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        }
        super.onResume();
    }

    void setViews(){
        moviePoster = (ImageView)findViewById(R.id.detail_movie_poster_iv);
        movieBackdrop = (ImageView) findViewById(R.id.detail_movie_thumbnail_iv);
        overViewTv = (TextView) findViewById(R.id.movie_description_tv);
        movieTitleTv = (TextView) findViewById(R.id.detail_movie_title_tv);
        movieReleaseDateTv = (TextView) findViewById(R.id.release_date_value_tv);
        movieRatingTv = (TextView) findViewById(R.id.movie_rating_value_tv);
        favoritesButton = (Button) findViewById(R.id.add_favorite_btn);
    }

    //api call to get movie reviews
    void getReviewsList() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.MOVIE_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            PopularMoviesService service = retrofit.create(PopularMoviesService.class);
            Call<VideoReviews> call = service.getVideoReviews(String.valueOf(movie.getId()));


            call.enqueue(new Callback<VideoReviews>() {

                @Override
                public void onResponse(Call<VideoReviews> call, retrofit2.Response<VideoReviews> response) {
                    reviewsList = (ArrayList<VideoReviews.Review>) response.body().getResults();
                    rvAdapter = new MovieReviewsAdapter(reviewsList);
                    reviewsRecyclerView.setAdapter(rvAdapter);

                }

                @Override
                public void onFailure(Call<VideoReviews> call, Throwable t) {
                    //toast lets user know that there was an error with receiving data from api

                    Toast.makeText(MovieDetailActivity.this, R.string.service_unavailable, Toast.LENGTH_SHORT).show();

                }
            });


        } else {
            // the connection is not available
            Toast.makeText(this, R.string.connection_unavailable, Toast.LENGTH_SHORT).show();
        }
    }

    //api call to get movie trailers
    void getVideoList() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.MOVIE_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            PopularMoviesService service = retrofit.create(PopularMoviesService.class);
            Call<VideoResults> call = service.getVideoResults(String.valueOf(movie.getId()));


            call.enqueue(new Callback<VideoResults>() {

                @Override
                public void onResponse(Call<VideoResults> call, retrofit2.Response<VideoResults> response) {
                    videosList = (ArrayList<VideoResults.VideoData>) response.body().getResults();
                    videoRvAdapter = new TrailersRecyclerViewAdapter(videosList,MovieDetailActivity.this);
                    videoRecyclerView.setAdapter(videoRvAdapter);

                }

                @Override
                public void onFailure(Call<VideoResults> call, Throwable t) {
                    //toast lets user know that there was an error with receiving data from api

                    Toast.makeText(MovieDetailActivity.this, R.string.service_unavailable, Toast.LENGTH_SHORT).show();

                }
            });


        } else {
            // the connection is not available
            Toast.makeText(this, R.string.connection_unavailable, Toast.LENGTH_SHORT).show();
        }
    }
    void setDetailRecyclerView() {
        reviewsRecyclerView = (RecyclerView) findViewById(R.id.reviews_recycler_view);
        rvLayoutManager = new LinearLayoutManager(this);
        reviewsRecyclerView.setLayoutManager(rvLayoutManager);
        videoRecyclerView = (RecyclerView) findViewById(R.id.trailer_recycler_view);
        videoRvLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        videoRecyclerView.setLayoutManager(videoRvLayoutManager);



    }

    //playing trailer in youtube app, if app not available video opens in browser
    @Override
    public void onTrailerItemClicked(int itemClickedPostion) {
        VideoResults.VideoData video = videosList.get(itemClickedPostion);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.VIDEO_YOUTUBE_APP_BASE_URL+ video.getKey()));
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
        boolean isIntentSafe = activities.size() > 0;

        if (isIntentSafe){

            startActivity(intent);
        }else{
            String url = Constants.VIDEO_YOUTUBE_BROWSER_BASE_URL+video.getKey();
            Intent browserIntent = new Intent(Intent.ACTION_VIEW);
            browserIntent.setData(Uri.parse(url));
            startActivity(browserIntent);
        }

    }
    //insert movie to favorites db
    public void insertFavorite(){
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                    favsDb.movieDao().insertFavorite(movie);


            }
        });
    }
    //delete movie from favorites db
    public void deleteFavorite(){
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                    favsDb.movieDao().delete(movie.getId());

            }
        });
    }


    void setSharedPreference(){
        preferences = PreferenceManager.getDefaultSharedPreferences(MovieDetailActivity.this);
        editor = preferences.edit();
    }
}
