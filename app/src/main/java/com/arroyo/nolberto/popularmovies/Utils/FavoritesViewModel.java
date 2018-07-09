package com.arroyo.nolberto.popularmovies.Utils;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.arroyo.nolberto.popularmovies.Model.Response;

import java.util.List;

public class FavoritesViewModel extends AndroidViewModel{
    private LiveData<List<Response.MoviesModel>> favorites;

    public FavoritesViewModel(@NonNull Application application) {
        super(application);
        FavoritesDatabase database = FavoritesDatabase.getDbInstance(getApplication());
        favorites = database.movieDao().loadAllFavorites();
    }

    public LiveData<List<Response.MoviesModel>> getFavorites() {
        return favorites;
    }
}
