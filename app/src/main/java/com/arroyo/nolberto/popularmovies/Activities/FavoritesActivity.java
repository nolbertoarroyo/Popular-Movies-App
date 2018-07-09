package com.arroyo.nolberto.popularmovies.Activities;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.arroyo.nolberto.popularmovies.Adapter.FavoritesRecyclerViewAdapter;
import com.arroyo.nolberto.popularmovies.Model.Response;
import com.arroyo.nolberto.popularmovies.R;
import com.arroyo.nolberto.popularmovies.Utils.FavoritesDatabase;
import com.arroyo.nolberto.popularmovies.Utils.FavoritesViewModel;

import java.util.List;

public class FavoritesActivity extends AppCompatActivity {
FavoritesDatabase favoritesDatabase;
RecyclerView favsRV;
RecyclerView.LayoutManager rVlayoutManager;
FavoritesRecyclerViewAdapter favsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        favsRV = (RecyclerView)findViewById(R.id.favsRV);
        rVlayoutManager = new LinearLayoutManager(FavoritesActivity.this);
        favsRV.setLayoutManager(rVlayoutManager);
        favsAdapter = new FavoritesRecyclerViewAdapter(FavoritesActivity.this);
        favsRV.setAdapter(favsAdapter);
        favoritesDatabase = FavoritesDatabase.getDbInstance(getApplicationContext());
        FavoritesViewModel viewModel = ViewModelProviders.of(this).get(FavoritesViewModel.class);
        viewModel.getFavorites().observe(this, new Observer<List<Response.MoviesModel>>() {
            @Override
            public void onChanged(@Nullable List<Response.MoviesModel> moviesModels) {
                Log.i("changed","data has been updated");
                favsAdapter.addItems(moviesModels);
            }
        });
    }
}
