package com.example.movies.api;

import com.google.gson.annotations.SerializedName;

public class TrailerResponse {
    @SerializedName("videos")
    private Videos videos;

    @Override
    public String toString() {
        return "TrailerResponse{" +
                "videos=" + videos +
                '}';
    }

    public TrailerResponse(Videos videos) {
        this.videos = videos;
    }

    public Videos getVideos() {
        return videos;
    }
}
