package com.example.movies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies.api.Movie;
import com.example.movies.viewmodel.FavouriteMoviesViewModel;

import java.util.List;

public class FavouriteMoviesActivity extends AppCompatActivity {
    private RecyclerView recyclerViewFavMovies;
    private MovieAdapter movieAdapter;
    private FavouriteMoviesViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_favourite_movies);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        viewModel = new ViewModelProvider(this).get(FavouriteMoviesViewModel.class);

        initViews();
        movieAdapter = new MovieAdapter();
        recyclerViewFavMovies.setAdapter(movieAdapter);
        movieAdapter.setOnMovieClickListener(new MovieAdapter.OnMovieClickListener() {
            @Override
            public void onMovieClick(Movie movie) {
                Intent intent = DetailMovieActivity.getIntent(FavouriteMoviesActivity.this, movie);
                startActivity(intent);
            }
        });

        viewModel.getAllFavMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                movieAdapter.setMovies(movies);
            }
        });

    }

    private void initViews(){
        recyclerViewFavMovies = findViewById(R.id.recyclerViewFavMovies);
    }

    public static Intent getIntent(Context context){
        Intent intent = new Intent(context, FavouriteMoviesActivity.class);
        return intent;
    }
}