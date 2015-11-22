package com.razor.movies.server.services;

import com.google.gson.Gson;
import com.razor.movies.server.models.MovieModel;
import com.razor.movies.server.providers.ModelProvider;
import spark.utils.StringUtils;

import java.util.List;

public class MovieService {

    private ModelProvider<MovieModel> provider;

    public MovieService() {
    }

    public MovieService setModelProvider(ModelProvider<MovieModel> provider) {
        this.provider = provider;
        return this;
    }

    public ModelProvider<MovieModel> getProvider() { return this.provider; }

    public List<MovieModel> findAll() {
        return this.getProvider().findAll();
    }

    public MovieModel findById(String id) {
        return this.getProvider().findById(id);
    }

    public List<MovieModel> find(String key, String value) {
        return this.getProvider().find(key, value);
    }

    /**
     * Insert or update an existing movie model
     * Throw an exception if the model does not have a name (very basic validation)
     * @param movieModel
     * @return
     * @throws ServiceException
     */

    public MovieModel update(MovieModel movieModel) throws ServiceException {
        if (StringUtils.isEmpty(movieModel.getMovieName())) {
            throw new ServiceException("Movie definition not valid");
        }
        return this.getProvider().update(movieModel, movieModel.getId());
    }

    public MovieModel update(String body) throws ServiceException {
        return update(new Gson().fromJson(body, MovieModel.class));
    }    

}
