/// <reference path='../refs.ts'/>

module auction.controllers {
    'use strict';

    export class MainController {
        static $inject = ['$scope', '$window'];

        constructor($scope, $window) {
            $scope.search = function (path:string) {
                $window.location.href = path;
            }
        }
    }

    angular.module('auction').controller('MainCtrl', MainController);
}
