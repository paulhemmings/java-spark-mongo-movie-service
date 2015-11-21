'use strict';

/*
 * Main Angular module
 *
 * Style guide:
 * avoid polluting global namespace:
 *  var app = angular.module('app');
 */

angular.module('MainApplicationModule', ['ui.router', 'ngAnimate']);

/*
 * Add SPA Routing using route provider
 *
 * Style guide:
 * avoid using a variable and instead use chaining with the getter syntax
 *
 */

angular
    .module('MainApplicationModule')
    .config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/movies');

    $stateProvider
        .state('movies', {
            url:'/movies',
            views: {
                'content': {
                    templateUrl: '/app/partials/movies.html',
                    controller: 'MoviesController'
                }
            }
        })
        .state('upload', {
            url:'/upload',
            views: {
                'content': {
                    templateUrl: '/app/partials/upload.html',
                    controller: 'UploadController'
                }
            }
        });
}]);
