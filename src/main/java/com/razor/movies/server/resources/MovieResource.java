package com.razor.movies.server.resources;

import com.razor.movies.server.models.MovieModel;
import com.razor.movies.server.services.MovieService;
import com.razor.movies.server.services.ServiceException;
import spark.Request;
import spark.Response;

import java.util.List;

import static com.razor.movies.server.utilities.JsonUtils.json;
import static spark.Spark.get;
import static spark.Spark.put;

public class MovieResource extends BaseResource {

    private MovieService movieService;

    /**
     * Construct a new set of end points related to movies
     * @param movieService
     */

    public MovieResource(MovieService movieService) {
        this.movieService = movieService;
        buildEndpoints();
    }

    /**
     * Return the movie service
     * @return
     */

    protected MovieService getMovieService() {
        return this.movieService;
    }

    /**
     * List all movies
     * @param request
     * @param response
     * @return
     */

    protected List<MovieModel> findAll(Request request, Response response) {
        return this.getMovieService().findAll();
    }

    /**
     * Find a movie by the ID
     * @param request
     * @param response
     * @return
     */

    protected MovieModel find(Request request, Response response) {
        return this.getMovieService().findById(request.params(":id"));
    }

    /**
     * Update a movie
     * @param request
     * @param response
     * @return
     * @throws ServiceException
     */


    protected MovieModel update(Request request, Response response) throws ServiceException {
        return this.getMovieService().update(request.body());
    }

    /**
     * Build end points. Run once as part of constructor
     */

    private void buildEndpoints() {
        get("/movies", "application/json", this::findAll, json());
        get("/movie/:id", "application/json", this::find, json());
        put("/movie", "application/json", this::update, json());
    }

}
