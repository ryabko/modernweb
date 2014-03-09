/// <reference path='../refs.ts'/>
var auction;
(function (auction) {
    (function (controllers) {
        'use strict';

        var MainController = (function () {
            function MainController($scope) {
                $scope.awesomeThings = [
                    'HTML5 Boilerplate',
                    'AngularJS',
                    'Karma'
                ];
            }
            MainController.$inject = ['$scope'];
            return MainController;
        })();
        controllers.MainController = MainController;

        angular.module('auction').controller('MainCtrl', MainController);
    })(auction.controllers || (auction.controllers = {}));
    var controllers = auction.controllers;
})(auction || (auction = {}));
//# sourceMappingURL=main.js.map
