'use strict';

angular
    .module('MainApplicationModule')
    .controller('UploadController', ['$scope', 'moviesService',
        function($scope, moviesService) {

            $scope.movie = null;
            $scope.uploadMovie = uploadMovie;
            $scope.status = [];

            function setStatus(status) {
                console.log(status);
                $scope.status.push(status);
                if ($scope.status.length > 5) {
                    $scope.status.splice(0, 1);
                }
            }

            function uploadMovie(movie) {
                setStatus("update movie");
                moviesService.updateMovie(movie).then(handleSuccess, handleError);
            }

            function handleSuccess(response) {
                setStatus("movie " + response.data.movie_name + " created");
                $scope.movie = JSON.stringify(response.data);
            }

            function handleError() {
                setStatus("an error occurred");
            }

            function initialize() {
            }

            initialize();

            /*
             * How do you unit test private methods?
             * Expose them via a "test" property
             */

            $scope['__test__'] = {
                handleSuccess: handleSuccess,
                handleError: handleError,
                setStatus: setStatus
            };

        }]);
