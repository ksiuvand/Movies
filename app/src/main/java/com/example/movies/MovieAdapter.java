package com.example.movies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movies.api.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private List<Movie> movies = new ArrayList<>();

    private OnReachedEndListener onReachedEndListener;
    private OnMovieClickListener onMovieClickListener;

    public void setOnMovieClickListener(OnMovieClickListener onMovieClickListener) {
        this.onMovieClickListener = onMovieClickListener;
    }

    public void setOnReachedEndListener(OnReachedEndListener onReachedEndListener) {
        this.onReachedEndListener = onReachedEndListener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        if (position >= movies.size()-5 && onReachedEndListener != null){
            onReachedEndListener.onReachEnd();
        }
        Movie movie = movies.get(position);
        double rating = Double.parseDouble(movie.getRating().getKp());
        int backgroundId;
        if (rating > 7){
            backgroundId = R.drawable.circle_green;
        }else if (rating > 5){
            backgroundId = R.drawable.circle_orange;
        }else{
            backgroundId = R.drawable.circle_red;
        }
        holder.textViewRating.setText(String.format("%.1f", rating));
        holder.textViewRating.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), backgroundId));
        Glide.with(holder.itemView)
                .load(movie.getPoster().getUrl())
                .into(holder.imageViewMovie);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onMovieClickListener.onMovieClick(movie);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    interface OnReachedEndListener{
        void onReachEnd();
    }

    interface OnMovieClickListener{
        void onMovieClick(Movie movie);
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageViewMovie;
        private TextView textViewRating;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewMovie = itemView.findViewById(R.id.imageViewMovie);
            textViewRating = itemView.findViewById(R.id.textViewRating);
        }
    }
}
