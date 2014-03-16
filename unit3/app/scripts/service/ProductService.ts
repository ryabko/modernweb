module auction.service {
    'use strict';

    import m = auction.model;

    export interface IProductService {
        getById(id: number): ng.IPromise<m.ProductModel>;
        getFeatured(): ng.IPromise<m.ProductModel[]>;
        search(): ng.IPromise<m.ProductModel[]>;
    }

    class ProductService implements IProductService {

        private static $inject = ['$http', '$log', '$q'];

        private static ERROR_MSG_PRODUCTS =
            "Can't get static JSON file with the list of all products. " +
            "Please, ensure you are running application on a web server";

        private static ERROR_MSG_FEATURED =
            "Can't get static JSON file with the list of featured products. " +
            "Please, ensure you are running application on a web server";

        private static ERROR_MSG_SEARCH =
            "Can't get static JSON file with the search result list. " +
            "Please, ensure you are running application on a web server";

        constructor(private $http: ng.IHttpService,
                    private $log: ng.ILogService,
                    private $q: ng.IQService) { }

        getById(id: number): ng.IPromise<m.ProductModel> {
            var product = this.$q.defer<m.ProductModel>();

            this.$http.get('data/products.json')
                .success((data) => {
                    var products = <m.ProductModel[]>data.items;
                    for (var i = 0; i < products.length; i++) {
                        if (products[i].id == id) {
                            return product.resolve(products[i]);
                        }
                    }
                    return product.reject();
                })
                .error(() => this.$log.error(ProductService.ERROR_MSG_PRODUCTS));

            return product.promise;
        }

        getFeatured(): ng.IPromise<m.ProductModel[]> {
            var products = this.$q.defer<m.ProductModel[]>();

            this.$http.get('data/featured-products.json')
                .success((data) => products.resolve(<m.ProductModel[]>data.items))
                .error(() => this.$log.error(ProductService.ERROR_MSG_FEATURED));

            return products.promise;
        }

        search(): ng.IPromise<m.ProductModel[]> {
            var products = this.$q.defer<m.ProductModel[]>();

            this.$http.get('data/search-results.json')
                .success((data) => products.resolve(<m.ProductModel[]>data.items))
                .error(() => this.$log.error(ProductService.ERROR_MSG_SEARCH));

            return products.promise;
        }

    }

    angular.module('auction').service('ProductService', ProductService);
}