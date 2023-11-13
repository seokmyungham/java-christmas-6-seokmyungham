package christmas.domain.event.discount;

import static christmas.domain.event.EventType.WEEKEND_DISCOUNT;

import christmas.domain.MenuType;
import christmas.domain.event.Event;
import christmas.domain.event.EventType;
import christmas.domain.event.VisitDate;
import christmas.domain.order.Order;

public class WeekendDiscount implements Event {
    private static final int YEAR = 2023, MONTH = 12;
    private static final int discountPrice = 2023;

    @Override
    public int apply(Order order, VisitDate visitDate) {
        if (visitDate.isWeekday(YEAR, MONTH)) {
            return order.countMenuType(MenuType.MAIN) * discountPrice;
        }
        return 0;
    }

    @Override
    public EventType getType() {
        return WEEKEND_DISCOUNT;
    }
}
