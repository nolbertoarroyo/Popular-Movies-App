package com.arroyo.nolberto.popularmovies.Activities;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.arroyo.nolberto.popularmovies.Adapter.FavoritesRecyclerViewAdapter;
import com.arroyo.nolberto.popularmovies.Model.Response;
import com.arroyo.nolberto.popularmovies.R;
import com.arroyo.nolberto.popularmovies.Utils.FavoritesDatabase;

import java.util.List;

public class FavoritesActivity extends AppCompatActivity {
FavoritesDatabase favoritesDatabase;
RecyclerView favsRV;
RecyclerView.LayoutManager layoutManager;
FavoritesRecyclerViewAdapter favsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        favoritesDatabase = FavoritesDatabase.getDbInstance(getApplicationContext());
        favsRV = (RecyclerView)findViewById(R.id.favsRV);
        layoutManager = new LinearLayoutManager(FavoritesActivity.this);
        favsAdapter = new FavoritesRecyclerViewAdapter(FavoritesActivity.this);
        favsRV.setAdapter(favsAdapter);

    final LiveData<List<Response.MoviesModel>> favorites = favoritesDatabase.movieDao().loadAllFavorites();
    favorites.observe(FavoritesActivity.this, new Observer<List<Response.MoviesModel>>() {
        @Override
        public void onChanged(@Nullable List<Response.MoviesModel> moviesModels) {

            favsAdapter.addItems(moviesModels);
        }
    });

    }
}
