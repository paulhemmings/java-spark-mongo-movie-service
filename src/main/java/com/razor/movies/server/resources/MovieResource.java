package com.razor.movies.server.resources;

import com.razor.movies.server.services.MovieService;

import static com.razor.movies.server.utilities.JsonUtils.json;
import static spark.Spark.get;
import static spark.Spark.put;

public class MovieResource {

    private MovieService movieService;

    public MovieResource() {
        buildEndpoints();
    }

    public MovieResource setMovieService(MovieService movieService) {
        this.movieService = movieService;
        return this;
    }

    private void buildEndpoints() {

        // READ
        get("/movies", "application/json", (request, response) ->
            movieService.findAll(),
            json()
        );

        // GET
        get("/movie/:id", "application/json", (request, response) ->
            movieService.findById(request.params(":id")),
            json()
        );

        // UPDATE
        put("/movie", "application/json", (request, response) ->
            movieService.update(request.body()),
            json()
        );
    }




}
