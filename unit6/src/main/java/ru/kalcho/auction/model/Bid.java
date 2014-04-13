package ru.kalcho.auction.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 */
public class Bid {
    private int id;
    private Product product;
    private BigDecimal amount;
    private int desiredQuantity; // How many items the user wants
    private User user;
    private LocalDateTime time;
    private boolean winning;

    public Bid() {
    }

    public Bid(int id, User user, Product product, BigDecimal amount) {
        this.id = id;
        this.user = user;
        this.product = product;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getDesiredQuantity() {
        return desiredQuantity;
    }

    public void setDesiredQuantity(int desiredQuantity) {
        this.desiredQuantity = desiredQuantity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public boolean isWinning() {
        return winning;
    }

    public void setWinning(boolean winning) {
        this.winning = winning;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bid bid = (Bid) o;

        if (id != bid.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Bid{" +
                "id=" + id +
                ", product=" + product +
                ", amount=" + amount +
                ", user=" + user +
                '}';
    }
}