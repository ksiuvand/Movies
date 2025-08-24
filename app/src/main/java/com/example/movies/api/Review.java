package com.example.movies.api;

import com.google.gson.annotations.SerializedName;

public class Review {
    @SerializedName("title")
    private String title;
    @SerializedName("type")
    private String type;
    @SerializedName("review")
    private String review;
    @SerializedName("date")
    private String date;
    @SerializedName("author")
    private String author;

    public Review(String title, String type, String review, String date, String author) {
        this.title = title;
        this.type = type;
        this.review = review;
        this.date = date;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getReview() {
        return review;
    }

    public String getDate() {
        return date;
    }

    public String getAuthor() {
        return author;
    }
}
