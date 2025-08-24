package com.example.movies.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.movies.MovieDao;
import com.example.movies.MovieDatabase;
import com.example.movies.api.Movie;

import java.util.List;

public class FavouriteMoviesViewModel extends AndroidViewModel {
    private MovieDao movieDao;

    public FavouriteMoviesViewModel(@NonNull Application application) {
        super(application);
        movieDao = MovieDatabase.getInstance(application).movieDao();
    }

    public LiveData<List<Movie>> getAllFavMovies(){
        return movieDao.getAllFavMovies();
    }

}
