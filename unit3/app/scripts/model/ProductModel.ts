module auction.model {
    'use strict';

    export class ProductModel {
        id: number;
        title: string;
        description: string;
        thumb: string;
        timeLeft: number;
        watchers: number;
        price: number;
        bids: number;
        seller: string;
    }
}