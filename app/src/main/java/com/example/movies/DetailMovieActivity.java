package com.example.movies;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movies.api.Movie;
import com.example.movies.api.Review;
import com.example.movies.api.Trailer;
import com.example.movies.viewmodel.DetailMovieViewModel;

import java.util.ArrayList;
import java.util.List;

public class DetailMovieActivity extends AppCompatActivity {

    private static final String EXTRA_MOVIE = "Movie";

    private TextView textViewTitle, textViewYear, textViewDescription;
    private ImageView imageViewMovie, imageViewStar;
    private RecyclerView recyclerViewTrailers, recyclerViewReviews;

    private TrailersAdapter trailersAdapter;
    private ReviewsAdapter reviewsAdapter;

    private DetailMovieViewModel detailMovieViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail_movie);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        detailMovieViewModel = new ViewModelProvider(this).get(DetailMovieViewModel.class);

        trailersAdapter = new TrailersAdapter();
        reviewsAdapter = new ReviewsAdapter();

        trailersAdapter.setOnTrailerClickListener(new TrailersAdapter.OnTrailerClickListener() {
            @Override
            public void onTrailerClick(Trailer trailer) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(trailer.getUrl()));
                startActivity(intent);
            }
        });

        initViews();

        Intent intent = getIntent();
        Movie movie = (Movie) intent.getSerializableExtra(EXTRA_MOVIE);

        detailMovieViewModel.loadTrailer(movie.getId());
        detailMovieViewModel.getTrailers().observe(this, new Observer<List<Trailer>>() {
            @Override
            public void onChanged(List<Trailer> trailers) {
                trailersAdapter.setTrailers((ArrayList<Trailer>) trailers);
            }
        });

        textViewTitle.setText(movie.getName());
        textViewYear.setText(String.valueOf(movie.getYear()));
        textViewDescription.setText(movie.getDescription());

        Glide.with(this).load(movie.getPoster().getUrl()).into(imageViewMovie);

        recyclerViewTrailers.setAdapter(trailersAdapter);
        recyclerViewReviews.setAdapter(reviewsAdapter);

        detailMovieViewModel.loadReviews(movie.getId());
        detailMovieViewModel.getReviewsList().observe(this, new Observer<List<Review>>() {
            @Override
            public void onChanged(List<Review> reviews) {
                reviewsAdapter.setReviews((ArrayList<Review>) reviews);
            }
        });
        Drawable starOn = ContextCompat.getDrawable(this, android.R.drawable.star_on);
        Drawable starOff = ContextCompat.getDrawable(this, android.R.drawable.star_off);

        detailMovieViewModel.getFavMovie(movie.getId()).observe(this, new Observer<Movie>() {
            @Override
            public void onChanged(Movie movieDB) {
                if (movieDB != null){
                    imageViewStar.setImageDrawable(starOn);
                    imageViewStar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            detailMovieViewModel.removeFavMovie(movie.getId());
                        }
                    });
                }else{
                    imageViewStar.setImageDrawable(starOff);
                    imageViewStar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            detailMovieViewModel.addFavMovie(movie);
                        }
                    });
                }
            }
        });
    }

    public static Intent getIntent(Context context, Movie movie){
        Intent intent = new Intent(context, DetailMovieActivity.class);
        intent.putExtra(EXTRA_MOVIE, movie);
        return intent;
    }

    private void initViews(){
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewYear = findViewById(R.id.textViewYear);
        textViewDescription = findViewById(R.id.textViewDescription);
        imageViewMovie = findViewById(R.id.imageViewMovie);
        recyclerViewTrailers = findViewById(R.id.recyclerViewTrailers);
        recyclerViewReviews = findViewById(R.id.recyclerViewReviews);
        imageViewStar = findViewById(R.id.imageViewStar);
    }
}