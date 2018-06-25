package com.arroyo.nolberto.popularmovies.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arroyo.nolberto.popularmovies.Interfaces.OnTrailerClickListener;
import com.arroyo.nolberto.popularmovies.Model.VideoResults;
import com.arroyo.nolberto.popularmovies.R;

import java.util.ArrayList;

public class TrailersRecyclerViewAdapter extends RecyclerView.Adapter<TrailersRecyclerViewAdapter.TrailerViewHolder> {
    private ArrayList<VideoResults.VideoData> videoList;
    Context context;
    final OnTrailerClickListener trailerClickListener;

    public TrailersRecyclerViewAdapter(ArrayList<VideoResults.VideoData> videoList, OnTrailerClickListener listener) {
        this.videoList = videoList;
        this.trailerClickListener = listener;
    }

    @Override
    public TrailerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        int listItem = R.layout.trailer_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(listItem,parent,false);
        return new TrailerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TrailerViewHolder holder, int position) {
        VideoResults.VideoData video = videoList.get(position);

        ImageView playImage = holder.trailerPlay;
        TextView videoText =  holder.videoLink;

        videoText.setText(video.getName());
        playImage.setImageResource(android.R.drawable.ic_media_play);


    }


    @Override
    public int getItemCount() {
        return videoList.size();
    }

    class TrailerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView trailerPlay;
        TextView videoLink;

        public TrailerViewHolder(View itemView) {
            super(itemView);

            this.trailerPlay = (ImageView)itemView.findViewById(R.id.play_image);
            this.videoLink = (TextView)itemView.findViewById(R.id.trailer_title);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int selectedTrailer = getAdapterPosition();
            trailerClickListener.onTrailerItemClicked(selectedTrailer);
        }
    }
}
