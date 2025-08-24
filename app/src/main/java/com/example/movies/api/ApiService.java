package com.example.movies.api;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("movie?token=17ZRGS2-Y9AMDTH-HPZ52TY-NGADSDD&rating.kp=7-10&limit=10&sortField=votes.kp&sortType=-1")
    Single<MovieResponse> loadMovies(@Query("page") int page);

    @GET("movie/{id}?token=17ZRGS2-Y9AMDTH-HPZ52TY-NGADSDD")
    Single<TrailerResponse> loadTrailers(@Path("id") int id);

    @GET("review?token=17ZRGS2-Y9AMDTH-HPZ52TY-NGADSDD")
    Single<ReviewResponse> loadReviews(@Query("movieId") int id);
}
