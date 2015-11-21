describe('MainApplicationModule', function () {

    var scope,
        controller;

    beforeEach(function () {
        module('MainApplicationModule');
    });

    describe('MoviesController', function () {

        beforeEach(inject(function ($rootScope, $controller) {
            scope = $rootScope.$new();
            controller = $controller('MoviesController', {
                '$scope': scope
            });
        }));

        it('should load movies', function() {
            expect(scope.movies.length).toBe(0);
            scope['__test__'].loadMovies({data: [{'name':'test-data'}]});
            expect(scope.movies.length).toBe(1);
        });

    });
});
