package christmas.domain.event.discount;

import static christmas.domain.event.EventType.STAR_DAY_DISCOUNT;

import christmas.domain.VisitDate;
import christmas.domain.event.Event;
import christmas.domain.event.EventType;
import christmas.domain.order.Order;
import java.util.ArrayList;
import java.util.List;

public class StarDayDiscount implements Event {
    private static final int discountPrice = 1000;
    private static final List<Integer> STAR_DAY = new ArrayList<>(List.of(3, 10, 17, 24, 25, 31));

    @Override
    public int apply(Order order, VisitDate visitDate) {
        if (meetRequirements(order, visitDate)) {
            return discountPrice;
        }
        return 0;
    }

    @Override
    public boolean meetRequirements(Order order, VisitDate visitDate) {
        return order.totalPrice() >= EVENT_REQUIREMENT && visitDate.isVisitInEventDay(STAR_DAY);
    }

    @Override
    public EventType getType() {
        return STAR_DAY_DISCOUNT;
    }
}
