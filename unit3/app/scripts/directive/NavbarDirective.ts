module auction.directive {
    'use strict';

    angular.module('auction')
        .directive('auctionNavbar', () => {
            return {
                scope: true,
                restrict: 'E',
                templateUrl: 'views/partial/navbar.html'
            }
        });

}