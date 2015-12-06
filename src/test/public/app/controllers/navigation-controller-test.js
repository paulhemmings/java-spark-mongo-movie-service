describe('MainApplicationModule', function () {

    var scope,
        controller;

    beforeEach(function () {
        module('MainApplicationModule');
    });

    describe('NavigationController', function () {

        beforeEach(inject(function ($rootScope, $controller) {
            scope = $rootScope.$new();
            controller = $controller('NavigationController', {
                '$scope': scope
            });
        }));

        it('should return true if page is active', function() {
            spyOn(scope, "getUrl").andReturn("http://hello");
            expect(scope.isActive('hello')).toBe(true);
            expect(scope.isActive('goodbye')).toBe(false);
        });

    });
});
