package ru.kalcho.auction.service

import ru.kalcho.auction.model.Bid
import ru.kalcho.auction.model.Product
import ru.kalcho.auction.model.User
import spock.lang.Shared
import spock.lang.Specification

/**
 *
 */
class BidServiceImplTest extends Specification {

    @Shared product = Stub(Product) {
        getMinimalPrice() >> BigDecimal.valueOf(10)
        getReservedPrice() >> BigDecimal.valueOf(30)
    }
    @Shared user = Stub(User) {
        getEmail() >> "test@example.com"
    }

    void setup() {
    }

    void cleanup() {
    }

    def "Place null bid should throw NPE"() {
        given:
        def bidService = new BidServiceImpl();

        when:
        bidService.placeBid(null)

        then:
        thrown(NullPointerException)
    }

    def "Placing bid with lower than minimal price should NOT be accepted"() {
        given:
        def bidService = new BidServiceImpl();
        def bid = new Bid(1, user, product, BigDecimal.valueOf(5))

        when:
        def accepted = bidService.placeBid(bid)

        then:
        !accepted
    }

    def "Placing bid with greater than minimal price should be accepted"() {
        given:
        def bidService = new BidServiceImpl();
        def bid = new Bid(1, user, product, BigDecimal.valueOf(15))

        when:
        def accepted = bidService.placeBid(bid)

        then:
        accepted
        !bid.isWinning()
    }

    def "Placing bid with greater than reserved price should be winning"() {
        given:
        def bidService = new BidServiceImpl();

        def bid = new Bid(1, user, product, BigDecimal.valueOf(35))

        when:
        def accepted = bidService.placeBid(bid)

        then:
        accepted
        bid.isWinning()
    }

    def "Top bid should have max amount (bids should be sorted by amount)"() {
        given:
        def bidService = new BidServiceImpl();

        when:
        bidService.placeBid(new Bid(1, user, product, BigDecimal.valueOf(10)))
        bidService.placeBid(new Bid(2, user, product, BigDecimal.valueOf(12)))
        bidService.placeBid(new Bid(3, user, product, BigDecimal.valueOf(8)))

        then:
        bidService.getCurrentTopBid(product).getAmount() == BigDecimal.valueOf(12)
    }

    def "Need notify overbidded users if they want"() {
        given:
        def bidService = new BidServiceImpl();
        def user1 = Mock(User) { getEmail() >> "test1@example.com"; isOverbidNotifications() >> false }
        def user2 = Mock(User) { getEmail() >> "test2@example.com"; isOverbidNotifications() >> true }
        def user3 = Mock(User) { getEmail() >> "test3@example.com"; isOverbidNotifications() >> true }
        def user4 = Mock(User) { getEmail() >> "test4@example.com"; isOverbidNotifications() >> true }

        def bid1 = new Bid(1, user1, product, BigDecimal.valueOf(10))
        def bid2 = new Bid(2, user2, product, BigDecimal.valueOf(11))
        def bid3 = new Bid(3, user3, product, BigDecimal.valueOf(12))
        def bid4 = new Bid(4, user4, product, BigDecimal.valueOf(13))

        when:
        bidService.placeBid(bid1)
        bidService.placeBid(bid2)
        bidService.placeBid(bid3)
        bidService.placeBid(bid4)

        then:
        bidService.getUsersToOverbidNotify(bid4).size() == 2
    }

    def "Product has no winner if nobody overbid reserved price"() {
        given:
        def bidService= new BidServiceImpl()
        def bid = new Bid(1, user, product, BigDecimal.valueOf(10))

        when:
        bidService.placeBid(bid)

        then:
        !bidService.hasWinner(product)
    }

    def "Product has winner if someone overbid reserved price"() {
        given:
        def bidService= new BidServiceImpl()
        def bid = new Bid(1, user, product, BigDecimal.valueOf(35))

        when:
        bidService.placeBid(bid)

        then:
        bidService.hasWinner(product)
    }

}
