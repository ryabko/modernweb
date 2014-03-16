/// <reference path='../refs.ts'/>

module auction.controller {
    'use strict';

    import s = auction.service;
    import m = auction.model;

    export interface IProductScope extends ng.IScope {
        model: ProductController;
    }

    export interface IProductRouteParams extends ng.route.IRouteParamsService {
        id: number;
    }

    export class ProductController {
        private static $inject = ['$scope', '$routeParams', 'ProductService'];

        public product: m.ProductModel;

        constructor(private $scope: IProductScope,
                    private $routeParams: IProductRouteParams,
                    private productService: s.IProductService) {
            this.$scope.model = this;

            this.productService.getById($routeParams.id)
                .then((product) => this.product = product);
        }
    }

    angular.module('auction').controller('ProductCtrl', ProductController);
}