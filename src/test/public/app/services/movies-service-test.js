describe('MainApplicationModule', function () {

    var service,
        mockHttp = {
        };

    beforeEach(function () {
        module('MainApplicationModule');
    });

    describe('testing moviesService', function () {

        beforeEach(inject(function (_moviesService_, $http) {
            service = _moviesService_,
            mockHttp = $http;
            spyOn(service, 'makeRequest');
        }));

        it("should list movies", function() {
            service.listMovies();
            expect(service.makeRequest).toHaveBeenCalledWith('/movies', {}, 'GET');
        });

        it("should update Movie", function() {
            var movie = { 'test': 'test'};
            service.updateMovie(movie);
            expect(service.makeRequest).toHaveBeenCalledWith('/movie', movie, 'PUT');
        });

        it("should get movie using id", function() {
            var movie = { 'id': 'id'};
            service.getMovie(movie);
            expect(service.makeRequest).toHaveBeenCalledWith('/movie/id', movie, 'GET');
        });
    });
});