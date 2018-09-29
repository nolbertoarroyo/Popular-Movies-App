package com.arroyo.nolberto.popularmovies.Activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
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
import com.arroyo.nolberto.popularmovies.Utils.FavoritesViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements OnListItemClickListener {
    private FavoritesViewModel viewModel;
    private RecyclerView recyclerView;
    private MovieRecyclerViewAdapter rvAdapter;
    private RecyclerView.LayoutManager rvLayoutManager;
    private ArrayList<com.arroyo.nolberto.popularmovies.Model.Response.MoviesModel> popularMoviesList;
    private Parcelable mstate;
    private String moviesToLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //checking savedInstanceState bundle to see if it has data
        if (savedInstanceState != null) {
            moviesToLoad = savedInstanceState.getString(Constants.MOVIES_LOADED_KEY);
        } else {
            moviesToLoad = Constants.POPULAR_MOVIES_SETTING;
        }

        viewModel = ViewModelProviders.of(this).get(FavoritesViewModel.class);

        setLaunchLists();
        setRecyclerView();


    }

    //saving scrollPosition and movies setting
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(Constants.MOVIES_LOADED_KEY, moviesToLoad);
        outState.putParcelable(Constants.RECYCLER_VIEW_STATE_KEY, rvLayoutManager.onSaveInstanceState());


    }


    @Override
    protected void onResume() {
        super.onResume();

        if (mstate != null) {
            rvLayoutManager.onRestoreInstanceState(mstate);
        }
    }

    //restoring scroll Position and setting it to recyclerView
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            moviesToLoad = savedInstanceState.getString(Constants.MOVIES_LOADED_KEY);
            mstate = savedInstanceState.getParcelable(Constants.RECYCLER_VIEW_STATE_KEY);

        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        viewModel.getFavorites().removeObservers(MainActivity.this);
        viewModel.getMoviesList().removeObservers(MainActivity.this);


        // checks which setting is selected and makes api call for either popular movies or top rated movies
        if (item.getItemId() == R.id.menu_sort_popular) {
            moviesToLoad = Constants.POPULAR_MOVIES_SETTING;
            viewModel.setMoviesToLoad(moviesToLoad);
            setMovieListObservers();
            viewModel.loadMovieList();


        } else if (item.getItemId() == R.id.menu_sort_rating) {
            moviesToLoad = Constants.TOP_RATED_MOVIES_SETTING;
            viewModel.setMoviesToLoad(moviesToLoad);
            setMovieListObservers();
            viewModel.loadMovieList();

        } else if (item.getItemId() == R.id.menu_Favorites) {
            moviesToLoad = Constants.FAVORITES_MOVIES_SETTING;
            viewModel.setMoviesToLoad(moviesToLoad);
            openFavoritesList();
        }
        return super.onOptionsItemSelected(item);
    }

    //method checks internet connection.  If connection is available Retrofit object is created to make api call and set recyclerView with received data
    void setMovieListObservers() {
        viewModel.getMoviesList().observe(this, new Observer<List<com.arroyo.nolberto.popularmovies.Model.Response.MoviesModel>>() {
            @Override
            public void onChanged(@Nullable List<com.arroyo.nolberto.popularmovies.Model.Response.MoviesModel> moviesModels) {
                popularMoviesList = (ArrayList<com.arroyo.nolberto.popularmovies.Model.Response.MoviesModel>) moviesModels;
                rvAdapter.addItems(moviesModels);
            }
        });
    }

    // Interface passes selected movie object to MovieDetailActivity
    @Override
    public void onListItemClicked(int itemClickedPostion) {

        openDetailWithSelectedItem(popularMoviesList, itemClickedPostion);


    }

    void setRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        rvLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(rvLayoutManager);
        rvAdapter = new MovieRecyclerViewAdapter(popularMoviesList, MainActivity.this);
        recyclerView.setAdapter(rvAdapter);


    }

    //repopulates recyclerView with favorites
    void openFavoritesList() {

        viewModel.getFavorites().observe(this, new Observer<List<com.arroyo.nolberto.popularmovies.Model.Response.MoviesModel>>() {
            @Override
            public void onChanged(@Nullable final List<com.arroyo.nolberto.popularmovies.Model.Response.MoviesModel> moviesModels) {

                popularMoviesList = (ArrayList<com.arroyo.nolberto.popularmovies.Model.Response.MoviesModel>) moviesModels;
                rvAdapter.addItems(moviesModels);
            }
        });
    }

    public void openDetailWithSelectedItem(List<com.arroyo.nolberto.popularmovies.Model.Response.MoviesModel> moviesList, int clickedPosition) {
        com.arroyo.nolberto.popularmovies.Model.Response.MoviesModel selectedMovie = moviesList.get(clickedPosition);
        Intent startDetailActivityIntent = new Intent(MainActivity.this, MovieDetailActivity.class);
        startDetailActivityIntent.putExtra(Constants.MOVIES_SELECTED_KEY, selectedMovie);
        startActivity(startDetailActivityIntent);
    }

    public void setLaunchLists() {
        //get and display default movie list
        if (moviesToLoad == Constants.FAVORITES_MOVIES_SETTING) {
            openFavoritesList();

        } else {

            setMovieListObservers();
        }
    }

}
