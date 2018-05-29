package com.arroyo.nolberto.popularmovies.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arroyo.nolberto.popularmovies.Constants;
import com.arroyo.nolberto.popularmovies.Interfaces.OnListItemClickListener;
import com.arroyo.nolberto.popularmovies.Model.Response;
import com.arroyo.nolberto.popularmovies.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieRecyclerViewAdapter.MovieViewHolder>{
    private ArrayList<Response.MoviesModel> moviesList;
    Context context;
    final OnListItemClickListener movieSelected;



    public MovieRecyclerViewAdapter(ArrayList<Response.MoviesModel> moviesList, OnListItemClickListener itemSelected) {
        this.moviesList = moviesList;
        this.movieSelected = itemSelected;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        context = parent.getContext();
        int listItem= R.layout.list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(listItem,parent,false);
        MovieViewHolder viewHolder = new MovieViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Response.MoviesModel movie = moviesList.get(position);
        ImageView movieImage = holder.movieThumbnail;
        TextView title = holder.movieTitle;
        String pic = Constants.MOVIE_POSTER_BASE_URL+ movie.getPoster_path();

        Picasso.with(context).load(pic).into(movieImage);
        title.setText(movie.getOriginal_title());


    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }


    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView movieThumbnail;
        TextView movieTitle;

        public MovieViewHolder(View itemView) {
            super(itemView);

            this.movieThumbnail = (ImageView)itemView.findViewById(R.id.movie_thumbnail_iv);
            this.movieTitle = (TextView)itemView.findViewById(R.id.movie_title_tv);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            movieSelected.onListItemClicked(clickedPosition);
        }

    }
}

