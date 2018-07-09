package com.arroyo.nolberto.popularmovies.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arroyo.nolberto.popularmovies.Model.Response;
import com.arroyo.nolberto.popularmovies.R;

import java.util.List;


public class FavoritesRecyclerViewAdapter extends RecyclerView.Adapter<FavoritesRecyclerViewAdapter.FavoritesViewHolder> {
        private Context context;
        private List<Response.MoviesModel> favorites;

    public FavoritesRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @Override
        public FavoritesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            context = parent.getContext();
            int listItem= R.layout.favs_list_tem;
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(listItem,parent,false);
            FavoritesViewHolder viewHolder = new FavoritesViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(FavoritesViewHolder holder, int position) {
            Response.MoviesModel favoriteMovie = favorites.get(position);
            TextView favoritesTitleTv = holder.favoritesTitle;
            favoritesTitleTv.setText(favoriteMovie.getTitle());

        }


        @Override
        public int getItemCount() {
            if (favorites == null) {
                return 0;
            }
            return favorites.size();
        }

    public void addItems(List<Response.MoviesModel> favs) {
        this.favorites = favs;
        notifyDataSetChanged();
    }
    public List<Response.MoviesModel> getFavorites() {
        return favorites;
    }

        class FavoritesViewHolder extends RecyclerView.ViewHolder{
            TextView favoritesTitle;


            public FavoritesViewHolder(View itemView) {
                super(itemView);

                this.favoritesTitle = (TextView)itemView.findViewById(R.id.favs_movie_title);

            }
        }

    }


