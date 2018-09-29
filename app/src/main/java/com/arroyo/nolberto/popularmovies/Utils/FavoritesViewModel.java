package com.arroyo.nolberto.popularmovies.Utils;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.arroyo.nolberto.popularmovies.Activities.MainActivity;
import com.arroyo.nolberto.popularmovies.Adapter.MovieRecyclerViewAdapter;
import com.arroyo.nolberto.popularmovies.Interfaces.PopularMoviesService;
import com.arroyo.nolberto.popularmovies.Model.Response;
import com.arroyo.nolberto.popularmovies.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FavoritesViewModel extends AndroidViewModel{
    private FavoritesDatabase database;
    private LiveData<List<Response.MoviesModel>> favorites;
    private MutableLiveData<List<Response.MoviesModel>> moviesList;
    private String moviesToLoad;

    public FavoritesViewModel(@NonNull Application application) {
        super(application);
        database = FavoritesDatabase.getDbInstance(getApplication());
    }

    public LiveData<List<Response.MoviesModel>> getFavorites() {
        if (favorites == null) {
            favorites = new MutableLiveData<>();
            getFavoritesFromDb();
        }
        return favorites;
    }

    public LiveData<List<Response.MoviesModel>> getMoviesList() {
        if (moviesList== null){
            moviesList = new MutableLiveData<List<Response.MoviesModel>>();
            loadMovieList();
        }
        return moviesList;
    }

    public void loadMovieList(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.MOVIE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PopularMoviesService service = retrofit.create(PopularMoviesService.class);
        Call<Response> call;
        if (moviesToLoad == Constants.TOP_RATED_MOVIES_SETTING) {
            call = service.getTopRatedMovies();

        } else {
            call = service.getPopularMovies();
        }

        call.enqueue(new Callback<Response>() {

            @Override
            public void onResponse(Call<com.arroyo.nolberto.popularmovies.Model.Response> call, retrofit2.Response<Response> response) {
                moviesList.setValue(response.body().getResults());

            }

            @Override
            public void onFailure(Call<com.arroyo.nolberto.popularmovies.Model.Response> call, Throwable t) {
                //toast lets user know that there was an error with receiving data from api


            }
        });
    }

    public void setMoviesToLoad(String moviesToLoad) {
        this.moviesToLoad = moviesToLoad;
        if(moviesToLoad != Constants.FAVORITES_MOVIES_SETTING){

            loadMovieList();
        }else {
            favorites=null;
            getFavorites();
        }

    }
    public void getFavoritesFromDb(){
        favorites = database.movieDao().loadAllFavorites();

    }

}
