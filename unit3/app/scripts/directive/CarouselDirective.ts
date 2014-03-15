module auction.directive {
    'use strict';

    angular.module('auction')
        .directive('auctionCarousel', () => {
            return {
                scope: true,
                restrict: 'E',
                templateUrl: 'views/partial/carousel.html'
            }
        });

}