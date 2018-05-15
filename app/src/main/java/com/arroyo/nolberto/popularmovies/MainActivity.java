package com.arroyo.nolberto.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.arroyo.nolberto.popularmovies.Interfaces.OnListItemClickListener;
import com.arroyo.nolberto.popularmovies.Interfaces.PopularMoviesService;

import java.lang.reflect.Array;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements OnListItemClickListener {
    private static String baseUrl = "https://api.themoviedb.org/";
    ArrayList<com.arroyo.nolberto.popularmovies.Model.Response.MoviesModel> popularMoviesList;
    private RecyclerView recyclerView;
    private MovieRecyclerViewAdapter rvAdapter;
    private RecyclerView.LayoutManager rvLayoutManager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getPopularList();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        rvLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(rvLayoutManager);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== R.id.sort_item){
            

        }
        return super.onOptionsItemSelected(item);
    }

    void getPopularList() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            PopularMoviesService service = retrofit.create(PopularMoviesService.class);
            Call<com.arroyo.nolberto.popularmovies.Model.Response> call = service.getPopularMovies();

            call.enqueue(new Callback<com.arroyo.nolberto.popularmovies.Model.Response>() {

                @Override
                public void onResponse(Call<com.arroyo.nolberto.popularmovies.Model.Response> call, Response<com.arroyo.nolberto.popularmovies.Model.Response> response) {
                    popularMoviesList = (ArrayList<com.arroyo.nolberto.popularmovies.Model.Response.MoviesModel>) response.body().getResults();
                    rvAdapter = new MovieRecyclerViewAdapter(popularMoviesList, MainActivity.this);
                    recyclerView.setAdapter(rvAdapter);

                    Log.d("movies", "onResponse: " + popularMoviesList.size());
                }

                @Override
                public void onFailure(Call<com.arroyo.nolberto.popularmovies.Model.Response> call, Throwable t) {

                }
            });


        } else {
            // the connection is not available
        }
    }

    @Override
    public void onListItemClicked(int itemClickedPostion) {
        com.arroyo.nolberto.popularmovies.Model.Response.MoviesModel selectedMovie = popularMoviesList.get(itemClickedPostion);
        Intent startDetailActivityIntent = new Intent(MainActivity.this, MovieDetailActivity.class);
        startDetailActivityIntent.putExtra("MovieSelected", selectedMovie);

        startActivity(startDetailActivityIntent);


    }
}
