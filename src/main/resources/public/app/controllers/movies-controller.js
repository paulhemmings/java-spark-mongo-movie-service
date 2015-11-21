'use strict';

angular
    .module('MainApplicationModule')
    .controller('MoviesController', ['$scope', 'moviesService',
        function($scope, moviesService) {

            $scope.movies = [];
            $scope.selectedMovie = null;
            $scope.buildStarLength = buildStarLength;
            $scope.selectMovie = selectMovie;
            $scope.isSelected = isSelected;
            $scope.hasSelected = hasSelected;

            function buildStarLength(movie) {
                if (movie === null) {
                    return 0;
                }
                return 3 + (movie.rating*14);
            }

            function selectMovie(movie) {
                if (isSelected(movie)) {
                    $scope.selectedMovie = null;
                    return;
                }
                $scope.selectedMovie = movie;
            }

            function isSelected(movie) {
                return $scope.selectedMovie === movie;
            }

            function hasSelected() {
                return $scope.selectedMovie !== null;
            }

            function loadMovies(response) {
                $scope.movies = response.data;
            }

            function initialize() {
                moviesService.listMovies().then(loadMovies);
            }

            initialize();

            /*
             * How do you unit test private methods?
             * Expose them via a "test" property
             */

            $scope['__test__'] = {
                loadMovies: loadMovies
            };

        }]);
