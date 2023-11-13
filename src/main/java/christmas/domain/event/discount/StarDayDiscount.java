package christmas.domain.event.discount;

import static christmas.domain.event.EventType.STAR_DAY_DISCOUNT;

import christmas.domain.event.Event;
import christmas.domain.event.EventType;
import christmas.domain.event.VisitDate;
import christmas.domain.order.Order;
import java.util.ArrayList;
import java.util.List;

public class StarDayDiscount implements Event {
    private static final int discountPrice = 1000;
    private static final List<Integer> STAR_DAY = new ArrayList<>(List.of(3, 10, 17, 24, 25, 31));

    @Override
    public int apply(Order order, VisitDate visitDate) {
        if (visitDate.isVisitInEventDay(STAR_DAY)) {
            return discountPrice;
        }
        return 0;
    }

    @Override
    public EventType getType() {
        return STAR_DAY_DISCOUNT;
    }
}
