package com.example.movies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies.api.Review;

import java.util.ArrayList;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ReviewViewHolder> {

    private ArrayList<Review> reviews = new ArrayList<>();


    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_item, parent, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        Review review = reviews.get(position);

        holder.textViewAuthor.setText(review.getAuthor());
        holder.textViewDate.setText(review.getDate());
        holder.textViewReview.setText(review.getReview());
        int colorId;
        if (review.getType().equals("Позитивный")){
            colorId = R.color.green;
        }else if (review.getType().equals("Нейтральный")){
            colorId = R.color.orange;
        }else{
            colorId = R.color.red;
        }
        holder.constraintLayout.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), colorId));
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
        notifyDataSetChanged();
    }

    class ReviewViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewAuthor, textViewReview, textViewDate;
        private ConstraintLayout constraintLayout;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewAuthor = itemView.findViewById(R.id.textViewAuthor);
            textViewReview = itemView.findViewById(R.id.textViewReview);
            textViewDate = itemView.findViewById(R.id.textViewDate);
            constraintLayout = itemView.findViewById(R.id.constraint);
        }
    }
}
