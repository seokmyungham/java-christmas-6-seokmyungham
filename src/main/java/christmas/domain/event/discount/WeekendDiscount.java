package christmas.domain.event.discount;

import christmas.domain.MenuType;
import christmas.domain.order.Order;

public class WeekendDiscount implements Discount {
    private static final int discountPrice = 2023;

    @Override
    public int apply(Order order) {
        return order.countMenuType(MenuType.MAIN) * discountPrice;
    }
}