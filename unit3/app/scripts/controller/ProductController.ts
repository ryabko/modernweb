/// <reference path='../refs.ts'/>

module auction.controller {
    'use strict';

    export interface IProductScope extends ng.IScope {
        model: ProductController;
    }

    export class ProductController {
        private static $inject = ['$scope'];

        constructor(private $scope: IProductScope) {
            this.$scope.model = this;
        }
    }

    angular.module('auction').controller('ProductCtrl', ProductController);
}