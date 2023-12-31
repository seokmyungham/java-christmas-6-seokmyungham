package christmas.domain.event.discount;

import static christmas.domain.event.EventType.CHRISTMAS_D_DAY_DISCOUNT;

import christmas.domain.VisitDate;
import christmas.domain.event.Event;
import christmas.domain.event.EventType;
import christmas.domain.order.Order;

public class DDayDiscount implements Event {
    private static final int CHRISTMAS_D_DAY = 25;
    private static final int STARTING_PRICE = 1000;
    private static final int DAILY_PRICE = 100;

    @Override
    public int apply(Order order, VisitDate visitDate) {
        if (meetRequirements(order, visitDate)) {
            return STARTING_PRICE + visitDate.daysElapsed() * DAILY_PRICE;
        }
        return 0;
    }

    @Override
    public boolean meetRequirements(Order order, VisitDate visitDate) {
        return order.totalPrice() >= EVENT_REQUIREMENT && visitDate.isVisitInRange(CHRISTMAS_D_DAY);
    }

    @Override
    public EventType getType() {
        return CHRISTMAS_D_DAY_DISCOUNT;
    }
}
