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

    def "should not insert a movie model object that is not fully defined"() {
        given:
            def movieModel = new MovieModel().setRating("10");
            ModelProvider<MovieModel> provider = Mock();
            MovieService movieService = new MovieService();
            movieService.setModelProvider(provider);
        when:
            movieService.update(movieModel);
        then:
            thrown(ServiceException);
    }

    def "should take out ID from movie model object"() {
        given:
            def movieModel = new MovieModel().setMovieName("the big test").setId("10");
            ModelProvider<MovieModel> provider = Mock();
            MovieService movieService = new MovieService();
            movieService.setModelProvider(provider);
        when:
            movieService.update(movieModel);
        then:
            1 * provider.update(_ as MovieModel, '10');
            notThrown(ServiceException)
    }

    def "should convert string into valid MovieModel object"() {
        given:
            def movieString = "{ \"movie_name\":\"the big test\", \"id\":\"10\" }";
            ModelProvider<MovieModel> provider = Mock();
            MovieService movieService = new MovieService();
            movieService.setModelProvider(provider);
        when:
            movieService.update(movieString);
        then:
            1 * provider.update(_ as MovieModel, '10');
            notThrown(ServiceException)
    }

}
