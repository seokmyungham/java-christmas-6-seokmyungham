package christmas.domain.event;

import static christmas.domain.Menu.CHAMPAGNE;
import static christmas.domain.event.EventType.GIFT_EVENT;

import christmas.domain.Menu;
import christmas.domain.VisitDate;
import christmas.domain.order.Order;
import java.util.HashMap;
import java.util.Map;

public class GiftEvent implements Event {
    private static final Menu GIFT = CHAMPAGNE;
    private static final int count = 1;
    private static final int EVENT_REQUIREMENT = 120000;

    @Override
    public int apply(Order order, VisitDate visitDate) {
        if (meetRequirements(order)) {
            return GIFT.getPrice() * count;
        }
        return 0;
    }

    @Override
    public boolean meetRequirements(Order order) {
        return order.totalPrice() >= EVENT_REQUIREMENT;
    }

    @Override
    public EventType getType() {
        return GIFT_EVENT;
    }


    public Map<Menu, Integer> getGiftInfo(Order order) {
        Map<Menu, Integer> giftInfo = new HashMap<>();
        if (meetRequirements(order)) {
            giftInfo.put(GIFT, count);
        }
        return giftInfo;
    }
}
