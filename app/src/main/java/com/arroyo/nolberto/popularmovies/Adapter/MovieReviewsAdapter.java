package com.arroyo.nolberto.popularmovies.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
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
        final TextView reviewContent = holder.reviewContent;
        final Button showMoreButton = holder.showMore;
        final Button showLessButton = holder.showLess;

        author.setText(review.getAuthor());
        reviewContent.setText(review.getContent());

        showMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMoreButton.setVisibility(View.GONE);
                showLessButton.setVisibility(View.VISIBLE);
                reviewContent.setMaxLines(Integer.MAX_VALUE);
            }
        });

        showLessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLessButton.setVisibility(View.GONE);
                showMoreButton.setVisibility(View.VISIBLE);
                reviewContent.setMaxLines(5);
            }
        });
    }


    @Override
    public int getItemCount() {
        return reviews.size();
    }

    class ReviewsViewHolder extends RecyclerView.ViewHolder{
        TextView reviewAuthorTv, reviewContent;
        Button showMore, showLess;


        public ReviewsViewHolder(View itemView) {
            super(itemView);

            this.reviewAuthorTv = (TextView)itemView.findViewById(R.id.author_tv);
            this.reviewContent = (TextView)itemView.findViewById(R.id.content_tv);
            this.showMore = (Button)itemView.findViewById(R.id.show_more_ib);
            this.showLess = (Button)itemView.findViewById(R.id.show_less_ib);
        }
    }
}
