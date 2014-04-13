package ru.kalcho.auction;

import ru.kalcho.auction.model.Bid;
import ru.kalcho.auction.model.Product;
import ru.kalcho.auction.model.User;
import ru.kalcho.auction.service.BidService;
import ru.kalcho.auction.service.BidServiceImpl;

import java.math.BigDecimal;
import java.util.*;

/**
 *
 */
public class Main {

    private static final Product[] PRODUCTS = {
            new Product(1, "IPhone 5S", BigDecimal.valueOf(100), BigDecimal.valueOf(200)),
            new Product(2, "IPhone 5C", BigDecimal.valueOf(80), BigDecimal.valueOf(200)),
            new Product(3, "Samsung Galaxy S3", BigDecimal.valueOf(50), BigDecimal.valueOf(150)),
            new Product(4, "Samsung Galaxy S4", BigDecimal.valueOf(100), BigDecimal.valueOf(200)),
            new Product(5, "Nokia 3310", BigDecimal.valueOf(20), BigDecimal.valueOf(100))
    };

    private static final User[] USERS = {
            new User(1, "Sergey", "sergey@example.com", true),
            new User(2, "Alex", "alex@example.com", false),
            new User(3, "Ivan", "ivan@example.com", false),
            new User(4, "Nikita", "nikita@example.com", true),
    };

    private static BidService bidService = new BidServiceImpl();

    // current processed products; winning products are removed from here
    private static List<Product> actualProducts = new ArrayList<>(Arrays.asList(PRODUCTS));

    private static Random random = new Random();
    private static int bidsIdGenerator = 0;

    public static void main(String[] args) {
        Timer timer = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                nextStep();

                if (checkFinish()) {
                    timer.cancel();
                    timer.purge();
                }
            }
        };

        timer.schedule(task, 0, 2000);
    }

    private static void nextStep() {
        System.out.println("========================================");

        User user = USERS[random.nextInt(USERS.length)];
        Product product = actualProducts.get(random.nextInt(actualProducts.size()));
        BigDecimal amount = BigDecimal.valueOf(random.nextInt(251) + 1);

        Bid bid = new Bid(++bidsIdGenerator, user, product, amount);
        System.out.println(String.format("[%s]: Placing bid %.2f for %s", user.getName(), amount, product.getTitle()));

        bidService.placeBid(bid);
    }

    private static boolean checkFinish() {
        actualProducts.removeIf(bidService::hasWinner);
        return actualProducts.isEmpty();

        // TODO: Move checking auction finish to BidService
    }

}
