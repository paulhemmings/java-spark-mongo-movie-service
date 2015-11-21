package com.razor.movies.server.services
import com.razor.movies.server.models.MovieModel
import com.razor.movies.server.providers.ModelProvider
import spock.lang.Specification

class MovieServiceSpec extends Specification {

    def "it should store provider"() {
        given:
            ModelProvider<MovieModel> provider = Mock();
            MovieService movieService = new MovieService();

        when:
            movieService.setModelProvider(provider);
            ModelProvider<MovieModel> storedProvider = movieService.getProvider();

        then:
            storedProvider == provider;
    }

    def "calling findAll should call correct provider method"() {
        given:
            ModelProvider<MovieModel> provider = Mock();
            MovieService movieService = new MovieService();
            movieService.setModelProvider(provider);
            List<MovieModel> results = new ArrayList<MovieModel>();

        when:
            List<MovieModel> modelList = movieService.findAll();

        then:
            1 * provider.findAll() >> results;
            modelList == results;
    }

}
