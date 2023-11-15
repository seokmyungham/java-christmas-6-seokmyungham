package christmas.domain.event.discount;

import static christmas.domain.event.EventType.WEEKEND_DISCOUNT;

import christmas.domain.MenuType;
import christmas.domain.VisitDate;
import christmas.domain.event.Event;
import christmas.domain.event.EventType;
import christmas.domain.order.Order;

public class WeekendDiscount implements Event {
    private static final int YEAR = 2023, MONTH = 12;
    private static final int discountPrice = 2023;

    @Override
    public int apply(Order order, VisitDate visitDate) {
        if (meetRequirements(order, visitDate)) {
            return order.countMenuType(MenuType.MAIN) * discountPrice;
        }
        return 0;
    }

    @Override
    public boolean meetRequirements(Order order, VisitDate visitDate) {
        return order.totalPrice() >= EVENT_REQUIREMENT && visitDate.isWeekend(YEAR, MONTH);
    }

    @Override
    public EventType getType() {
        return WEEKEND_DISCOUNT;
    }
}
