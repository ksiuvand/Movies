package com.example.movies.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Videos {
    @Override
    public String toString() {
        return "Videos{" +
                "trailers=" + trailers +
                '}';
    }

    @SerializedName("trailers")
    private List<Trailer> trailers;

    public Videos(List<Trailer> trailers) {
        this.trailers = trailers;
    }

    public List<Trailer> getTrailers() {
        return trailers;
    }
}
