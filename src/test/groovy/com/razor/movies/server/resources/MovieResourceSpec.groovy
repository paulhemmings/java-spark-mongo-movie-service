package com.razor.movies.server.resources

import com.razor.movies.server.services.MovieService
import spark.Request
import spark.Response
import spock.lang.Specification

/**
 * Created by paulhemmings on 12/6/15.
 */

class MovieResourceSpec extends Specification {

    def "it should initialize service and build end points on constructor"() {
        given:
            MovieService movieService = new MovieService()
            MovieResource movieResource
        when:
            movieResource = new MovieResource(movieService)
        then:
            movieResource.getMovieService() == movieService;
    }

    def "find all should call findAll on service"() {
        given:
            def request = Mock(Request.class)
            def response = Mock(Response.class)
            def movieService = Mock(MovieService.class)
            def movieResource = new MovieResource(movieService)
        when:
            movieResource.findAll(request, response)
        then:
            1 * movieService.findAll()
    }

    def "find one should call find on service and pass id parameter from request"() {
        given:
            def request = Mock(Request.class)
            def response = Mock(Response.class)
            def movieService = Mock(MovieService.class)
            def movieResource = new MovieResource(movieService)
        when:
            movieResource.find(request, response)
        then:
            1 * request.params(":id") >> "id"
            1 * movieService.findById("id")
    }

    def "update should call update on service and pass body from request"() {
        given:
            def request = Mock(Request.class)
            def response = Mock(Response.class)
            def movieService = Mock(MovieService.class)
            def movieResource = new MovieResource(movieService)
        when:
            movieResource.update(request, response)
        then:
            1 * request.body() >> "movie-body"
            1 * movieService.update("movie-body")
    }
}
