module auction.directive {
    'use strict';

    angular.module('auction')
        .directive('auctionAdvancedSearch', () => {
            return {
                scope: true,
                restrict: 'E',
                templateUrl: 'views/partial/advanced-search.html'
            }
        });

}