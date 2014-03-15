module auction.model {
    'use strict';

    export class ProductModel {
        title: string;
        description: string;
        thumb: string;
        timeLeft: number;
        watchers: number;
        price: number;
        url: string;
    }
}