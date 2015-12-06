'use strict';

angular
    .module('MainApplicationModule')
    .controller('NavigationController', ['$scope',
        function($scope) {

            $scope.getUrl = function() {
                return document.URL;
            };

            $scope.isActive = function(page) {
                return $scope.getUrl().indexOf(page) > 0;
            };

        }]);
