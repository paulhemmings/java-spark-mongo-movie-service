package com.razor.movies.server.models;

import com.google.gson.annotations.SerializedName;
import org.mongodb.morphia.annotations.Entity;

@Entity("movies")
public class MovieModel extends com.razor.movies.server.models.MongoModel {

    @SerializedName("movie_name")
    private String movieName;

    @SerializedName("image_url")
    private String imageUrl;

    private String rating;
    private String description;

    public String getMovieName() {
        return movieName;
    }

    public MovieModel setMovieName(String movieName) {
        this.movieName = movieName;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public MovieModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getRating() {
        return rating;
    }

    public MovieModel setRating(String rating) {
        this.rating = rating;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public MovieModel setDescription(String description) {
        this.description = description;
        return this;
    }
}
