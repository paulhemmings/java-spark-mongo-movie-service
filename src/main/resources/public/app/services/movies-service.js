'use strict';

/*
 * Data provider service.
 * Used to retrieve data from external service
 *
 * Style guide:
 * avoid using a variable and instead use chaining with the getter syntax
 * produces more readable code and avoids variable collisions or leaks.
 *
 */

angular
    .module('MainApplicationModule')
    .service('moviesService', function($http) {

        function rootServiceUrl() {
            return 'http://localhost:4567';
        }
        
        function listMovies() {
            return $http({
                url: rootServiceUrl() + '/movies',
                method: 'GET'
            });
        }

        function updateMovie(movie) {
            return $http({
                url: rootServiceUrl() + '/movie',
                data: movie,
                method: 'PUT'
            });
        }

        function getMovie(movie) {
            return $http({
                url: rootServiceUrl() + '/movie/' + movie.id,
                method: 'GET'
            });
        }

        return {
            listMovies: listMovies,
            updateMovie: updateMovie,
            getMovie: getMovie
        };

    });
