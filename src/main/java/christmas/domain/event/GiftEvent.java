package christmas.domain.event;

import static christmas.domain.Menu.CHAMPAGNE;
import static christmas.domain.event.EventType.GIFT_EVENT;

import christmas.domain.Menu;
import christmas.domain.order.Order;
import java.util.HashMap;
import java.util.Map;

public class GiftEvent implements Event{
    private static final Menu GIFT = CHAMPAGNE;
    private static final int count = 1;

    @Override
    public int apply(Order order, VisitDate visitDate) {
        if (meetRequirements(order)) {
            return GIFT.getPrice() * count;
        }
        return 0;
    }

    @Override
    public EventType getType() {
        return GIFT_EVENT;
    }

    public boolean meetRequirements(Order order) {
        return order.totalPrice() >= 120000;
    }

    public Map<Menu, Integer> getGift(Order order) {
        Map<Menu, Integer> giftInfo = new HashMap<>();
        if (meetRequirements(order)) {
            giftInfo.put(GIFT, count);
        }
        return giftInfo;
    }
}
