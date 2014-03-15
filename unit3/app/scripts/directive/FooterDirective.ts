module auction.directive {
    'use strict';

    angular.module('auction')
        .directive('auctionFooter', () => {
            return {
                scope: true,
                restrict: 'E',
                templateUrl: 'views/partial/footer.html'
            }
        });

}