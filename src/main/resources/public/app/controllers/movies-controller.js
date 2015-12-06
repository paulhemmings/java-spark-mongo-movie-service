'use strict';

angular
    .module('MainApplicationModule')
    .controller('MoviesController', ['$scope', 'moviesService',
        function($scope, moviesService) {

            $scope.movies = [];
            $scope.selectedMovie = null;

            $scope.buildStarLength = function(movie) {
                if (movie === null) {
                    return 0;
                }
                return 3 + (movie.rating*14);
            };

            $scope.isSelected = function(movie) {
                return $scope.selectedMovie === movie;
            };

            $scope.hasSelected = function() {
                return $scope.selectedMovie !== null;
            };


            $scope.selectMovie = function(movie) {
                if ($scope.isSelected(movie)) {
                    $scope.selectedMovie = null;
                    return;
                }
                $scope.selectedMovie = movie;
            };

            function loadMovies(response) {
                $scope.movies = response.data;
            }

            function initialize() {
                moviesService.listMovies().then(loadMovies);
            }

            initialize();

            /*
             * Expose private methods via a "test" property
             */

            $scope.__test__ = {
                loadMovies: loadMovies
            };

        }]);
