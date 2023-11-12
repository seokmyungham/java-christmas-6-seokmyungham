package christmas.domain.event.discount;

import christmas.domain.MenuType;
import christmas.domain.order.Order;

public class WeekdayDiscount implements Discount {
    private static final int discountPrice = 2023;

    @Override
    public int applyDiscount(Order order) {
        return order.countMenuType(MenuType.DESSERT) * discountPrice;
    }
}
