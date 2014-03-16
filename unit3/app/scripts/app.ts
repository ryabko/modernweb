/// <reference path='refs.ts'/>

//module auction {
//    'use strict';

angular.module('auction', ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'views/main.html',
                controller: 'MainCtrl'
            })
            .when('/search', {
                templateUrl: 'views/search.html',
                controller: 'SearchCtrl'
            })
            .when('/product', {
                templateUrl: 'views/product.html',
                controller: 'ProductCtrl'
            })
            .otherwise({
                redirectTo: '/'
            });
    }]);
//}