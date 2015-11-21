'use strict';

angular
    .module('MainApplicationModule')
    .controller('UploadController', ['$scope', 'moviesService',
        function($scope, moviesService) {

            $scope.movie = null;
            $scope.uploadMovie = uploadMovie;

            function uploadMovie(movie) {
                moviesService.updateMovie(movie).then(handleResponse);
            }

            function handleResponse(response) {
                $scope.movie = JSON.stringify(response.data);
            }

            function initialize() {
            }

            initialize();

            /*
             * How do you unit test private methods?
             * Expose them via a "test" property
             */

            $scope['__test__'] = {
                handleResponse: handleResponse
            };

        }]);
