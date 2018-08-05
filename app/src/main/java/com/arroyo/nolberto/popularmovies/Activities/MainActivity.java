package com.arroyo.nolberto.popularmovies.Activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.arroyo.nolberto.popularmovies.Adapter.MovieRecyclerViewAdapter;
import com.arroyo.nolberto.popularmovies.Utils.Constants;
import com.arroyo.nolberto.popularmovies.Interfaces.OnListItemClickListener;
import com.arroyo.nolberto.popularmovies.Interfaces.PopularMoviesService;
import com.arroyo.nolberto.popularmovies.R;
import com.arroyo.nolberto.popularmovies.Utils.FavoritesDatabase;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements OnListItemClickListener {
    private RecyclerView recyclerView;
    private MovieRecyclerViewAdapter rvAdapter;
    private RecyclerView.LayoutManager rvLayoutManager;
    private ArrayList<com.arroyo.nolberto.popularmovies.Model.Response.MoviesModel> popularMoviesList;
    private final String MOVIES_LOADED_KEY = "moviesLoaded";
    private final String SCROLL_POSITION_KEY = "scrollPosition";
    private String moviesToLoad;
    private int scrollPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //checking savedInstanceState bundle to see if it has data
        if (savedInstanceState != null) {
            moviesToLoad = savedInstanceState.getString(MOVIES_LOADED_KEY);
        }

        //get and display default movie list
        getMovieList();
        setRecyclerView();


    }

    //saving scrollPosition and movies setting
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        scrollPosition = recyclerView.getScrollState();
        outState.putInt(SCROLL_POSITION_KEY, scrollPosition);
        outState.putString(MOVIES_LOADED_KEY, moviesToLoad);


    }


    //restoring scroll Position and setting it to recyclerView
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            moviesToLoad = savedInstanceState.getString(MOVIES_LOADED_KEY);
            scrollPosition = savedInstanceState.getInt(SCROLL_POSITION_KEY);
            recyclerView.setScrollingTouchSlop(scrollPosition);


        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // checks which setting is selected and makes api call for either popular movies or top rated movies
        if (item.getItemId() == R.id.menu_sort_popular) {
            moviesToLoad = Constants.POPULAR_MOVIES_SETTING;
            getMovieList();


        } else if (item.getItemId() == R.id.menu_sort_rating) {
            moviesToLoad = Constants.TOP_RATED_MOVIES_SETTING;
            getMovieList();
        } else if (item.getItemId() == R.id.menu_Favorites) {
            openFavoritesList();
        }
        return super.onOptionsItemSelected(item);
    }

    //method checks internet connection.  If connection is available Retrofit object is created to make api call and set recyclerView with received data
    void getMovieList() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.MOVIE_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            PopularMoviesService service = retrofit.create(PopularMoviesService.class);
            Call<com.arroyo.nolberto.popularmovies.Model.Response> call;
            if (moviesToLoad == Constants.TOP_RATED_MOVIES_SETTING) {
                call = service.getTopRatedMovies();

            } else {
                call = service.getPopularMovies();
            }

            call.enqueue(new Callback<com.arroyo.nolberto.popularmovies.Model.Response>() {

                @Override
                public void onResponse(Call<com.arroyo.nolberto.popularmovies.Model.Response> call, Response<com.arroyo.nolberto.popularmovies.Model.Response> response) {
                    popularMoviesList = (ArrayList<com.arroyo.nolberto.popularmovies.Model.Response.MoviesModel>) response.body().getResults();
                    rvAdapter = new MovieRecyclerViewAdapter(popularMoviesList, MainActivity.this);
                    recyclerView.setAdapter(rvAdapter);

                }

                @Override
                public void onFailure(Call<com.arroyo.nolberto.popularmovies.Model.Response> call, Throwable t) {
                    //toast lets user know that there was an error with receiving data from api

                    Toast.makeText(MainActivity.this, R.string.service_unavailable, Toast.LENGTH_SHORT).show();

                }
            });


        } else {
            // the connection is not available
            Toast.makeText(this, R.string.connection_unavailable, Toast.LENGTH_SHORT).show();
        }
    }

    // Interface passes selected movie object to MovieDetailActivity
    @Override
    public void onListItemClicked(int itemClickedPostion) {
        com.arroyo.nolberto.popularmovies.Model.Response.MoviesModel selectedMovie = popularMoviesList.get(itemClickedPostion);
        Intent startDetailActivityIntent = new Intent(MainActivity.this, MovieDetailActivity.class);
        startDetailActivityIntent.putExtra(Constants.MOVIES_SELECTED_KEY, selectedMovie);
        startActivity(startDetailActivityIntent);


    }

    void setRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        rvLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(rvLayoutManager);

    }

    //opens users favorite movies list in FavoritesActivity
    void openFavoritesList() {

        Intent openFavoritesIntent = new Intent(this, FavoritesActivity.class);
        startActivity(openFavoritesIntent);
    }
}
