package com.arroyo.nolberto.popularmovies.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arroyo.nolberto.popularmovies.Model.VideoReviews;
import com.arroyo.nolberto.popularmovies.R;

import java.util.ArrayList;

public class MovieReviewsAdapter extends RecyclerView.Adapter<MovieReviewsAdapter.ReviewsViewHolder>{
    private ArrayList<VideoReviews.Review> reviews;
    private Context context;


    public MovieReviewsAdapter(ArrayList<VideoReviews.Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public ReviewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        int listItem= R.layout.review_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(listItem,parent,false);
        ReviewsViewHolder viewHolder = new ReviewsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ReviewsViewHolder holder, int position) {

        VideoReviews.Review review = reviews.get(position);
        TextView author = holder.reviewAuthorTv;
        TextView reviewContent = holder.reviewContent;

        author.setText(review.getAuthor());
        reviewContent.setText(review.getContent());


    }


    @Override
    public int getItemCount() {
        return reviews.size();
    }

    class ReviewsViewHolder extends RecyclerView.ViewHolder{
        TextView reviewAuthorTv, reviewContent;


        public ReviewsViewHolder(View itemView) {
            super(itemView);

            this.reviewAuthorTv = (TextView)itemView.findViewById(R.id.author_tv);
            this.reviewContent = (TextView)itemView.findViewById(R.id.content_tv);
        }
    }
}
