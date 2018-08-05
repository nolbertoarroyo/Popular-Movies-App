package com.arroyo.nolberto.popularmovies.Activities;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.widget.Toast;

import com.arroyo.nolberto.popularmovies.Adapter.FavoritesRecyclerViewAdapter;
import com.arroyo.nolberto.popularmovies.Model.Response;
import com.arroyo.nolberto.popularmovies.R;
import com.arroyo.nolberto.popularmovies.Utils.AppExecutors;
import com.arroyo.nolberto.popularmovies.Utils.Constants;
import com.arroyo.nolberto.popularmovies.Utils.FavoritesDatabase;
import com.arroyo.nolberto.popularmovies.Utils.FavoritesViewModel;

import java.util.List;

public class FavoritesActivity extends AppCompatActivity {
    private FavoritesDatabase favoritesDatabase;
    private RecyclerView favsRV;
    private RecyclerView.LayoutManager rVlayoutManager;
    private FavoritesRecyclerViewAdapter favsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        ActionBar actionBar = getSupportActionBar();

        // Enable the Up button
        if (actionBar != null){

            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        setRecyclerView();

        //adding swipe to delete favorites movie from database and update favorites boolean in SharedPreferences
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, final RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {

                return false;
            }

            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction) {
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        int position = viewHolder.getAdapterPosition();
                        List<Response.MoviesModel> selectedItem = favsAdapter.getFavorites();
                        favoritesDatabase.movieDao().delete(selectedItem.get(position).getId());
                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(FavoritesActivity.this);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putBoolean(Constants.FAVORITES_EXISTS_KEY+selectedItem.get(position).getId(),false).apply();
                    }
                });
            }
        }).attachToRecyclerView(favsRV);


        favoritesDatabase = FavoritesDatabase.getDbInstance(getApplicationContext());
        FavoritesViewModel viewModel = ViewModelProviders.of(this).get(FavoritesViewModel.class);
        viewModel.getFavorites().observe(this, new Observer<List<Response.MoviesModel>>() {
            @Override
            public void onChanged(@Nullable List<Response.MoviesModel> moviesModels) {
                favsAdapter.addItems(moviesModels);
            }
        });
    }

    private void setRecyclerView() {
        favsRV = (RecyclerView) findViewById(R.id.favsRV);
        rVlayoutManager = new LinearLayoutManager(FavoritesActivity.this);
        favsRV.setLayoutManager(rVlayoutManager);
        favsAdapter = new FavoritesRecyclerViewAdapter(FavoritesActivity.this);
        favsRV.setAdapter(favsAdapter);
    }
}
