package ru.kalcho.auction.service;

import ru.kalcho.auction.model.Bid;
import ru.kalcho.auction.model.Product;

import java.util.List;

/**
 *
 */
public interface BidService {

    public boolean placeBid(Bid bid);

    public Bid getCurrentTopBid(Product product);

    public boolean hasWinner(Product product);

}
