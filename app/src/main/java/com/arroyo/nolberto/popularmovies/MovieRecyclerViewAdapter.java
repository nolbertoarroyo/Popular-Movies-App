package com.arroyo.nolberto.popularmovies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arroyo.nolberto.popularmovies.Model.MovieModel;

import java.util.ArrayList;

public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieViewHolder>{
    ArrayList<MovieModel> moviesList;

    public MovieRecyclerViewAdapter(ArrayList<MovieModel> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        int listItem= R.layout.list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(listItem,parent,false);
        MovieViewHolder viewHolder = new MovieViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }


}
class MovieViewHolder extends RecyclerView.ViewHolder{

    ImageView movieThumbnail;
    TextView movieTitle;

    public MovieViewHolder(View itemView) {
        super(itemView);

        this.movieThumbnail = (ImageView)itemView.findViewById(R.id.movie_thumbnail_iv);
        this.movieTitle = (TextView)itemView.findViewById(R.id.movie_title_tv);
    }
}
