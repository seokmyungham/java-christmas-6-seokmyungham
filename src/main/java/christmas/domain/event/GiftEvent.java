package christmas.domain.event;

import static christmas.domain.Menu.CHAMPAGNE;
import static christmas.domain.event.EventType.GIFT_EVENT;

import christmas.domain.Menu;
import christmas.domain.order.Order;

public class GiftEvent implements Event{
    private static final Menu GIFT = CHAMPAGNE;

    @Override
    public int apply(Order order, VisitDate visitDate) {
        if (order.totalPrice() >= 120000) {
            return GIFT.getPrice();
        }
        return 0;
    }

    @Override
    public EventType getType() {
        return GIFT_EVENT;
    }
}
