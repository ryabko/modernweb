package ru.kalcho.auction.service;

import ru.kalcho.auction.model.Bid;
import ru.kalcho.auction.model.Product;
import ru.kalcho.auction.model.User;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 */
public class BidServiceImpl implements BidService {

    private Map<Product, List<Bid>> currentBids;

    public BidServiceImpl() {
        this.currentBids = new HashMap<>();
    }

    @Override
    public boolean placeBid(Bid bid) {
        if (!checkMinPrice(bid)) {
            sendNotification(bid.getUser(), "Sorry. Your bid is rejected (it is lower than min price).");
            return false;
        }

        addBid(bid);
        sendNotification(bid.getUser(), "Your bid is accepted.");

        if (checkWinning(bid)) {
            bid.setWinning(true);
            sendNotification(bid.getUser(), "Congratulations! Your bid is winning");
        }

        getUsersToOverbidNotify(bid).forEach(u -> sendNotification(u, "Your bid is overbidded"));

        return true;
    }

    private void addBid(Bid bid) {
        List<Bid> productBids = currentBids.get(bid.getProduct());
        if (productBids == null) {
            productBids = new ArrayList<>();
            currentBids.put(bid.getProduct(), productBids);
        }
        productBids.add(bid);
        productBids.sort((bid1, bid2) -> bid2.getAmount().compareTo(bid1.getAmount()));
    }

    @Override
    public Bid getCurrentTopBid(Product product) {
        List<Bid> productBids = currentBids.get(product);
        if (productBids != null && !productBids.isEmpty())
            return productBids.get(0);
        return null;
    }

    @Override
    public boolean hasWinner(Product product) {
        // TODO: Check product's endAuctionTime

        Bid topBid = getCurrentTopBid(product);
        return topBid != null && topBid.isWinning();
    }

    private boolean checkMinPrice(Bid bid) {
        // true if greater min price
        return bid.getAmount().compareTo(bid.getProduct().getMinimalPrice()) >= 0;
    }

    private boolean checkWinning(Bid bid) {
        // win if overbid reserved price
        return bid.getAmount().compareTo(bid.getProduct().getReservedPrice()) >= 0;
    }

    Set<User> getUsersToOverbidNotify(Bid bid) {
        List<Bid> productBids = currentBids.get(bid.getProduct());

        return productBids.stream()
                // find overbidded
                .filter(b -> b.getAmount().compareTo(bid.getAmount()) < 0)
                // find need notifications users but not himself
                .filter(b -> b.getUser().isOverbidNotifications() && !b.getUser().equals(bid.getUser()))
                // map to user
                .map(Bid::getUser)
                // collect to set for having unique users
                .collect(Collectors.toSet());
    }

    private void sendNotification(User user, String message) {
        // TODO: call NotificationService
        System.out.println(String.format("[BidService]: Message to %s: %s", user.getEmail(), message));
    }

}
