'use strict';

/*
 * Data provider service.
 * Used to retrieve data from external service
 */

angular
    .module('MainApplicationModule')
    .service('moviesService', function($http) {

        return {

            makeRequest: function (url, data, method) {
                return $http({
                    url: url,
                    data: data,
                    method: method
                });
            },

            listMovies: function () {
                return this.makeRequest('/movies', {}, 'GET');
            },

            updateMovie: function (movie) {
                return this.makeRequest('/movie', movie, 'PUT');
            },

            getMovie: function (movie) {
                return this.makeRequest('/movie/' + movie.id, movie, 'GET');
            }
        };

    });
