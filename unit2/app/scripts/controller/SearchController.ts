/// <reference path='../refs.ts'/>

module auction.controller {
    'use strict';

    import s = auction.service;
    import m = auction.model;

    export interface ISearchScope extends ng.IScope {
        model: SearchController;
    }

    export class SearchController {

        private static $inject = ['$scope', 'ProductService'];

        public searchResults: m.ProductModel[];

        constructor(private $scope: ISearchScope,
                    private productService: s.IProductService) {
            this.$scope.model = this;
            this.productService.search()
                .then((products) => this.searchResults = products);
        }
    }

    angular.module('auction').controller('SearchCtrl', SearchController);
}