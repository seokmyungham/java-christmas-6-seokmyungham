package christmas.domain.event.discount;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.order.Count;
import christmas.domain.order.MenuName;
import christmas.domain.order.Order;
import christmas.domain.order.OrderMenu;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WeekendDiscountTest {
    private Order totalOrder;

    @BeforeEach
    void setUp() {
        totalOrder = new Order(List.of(
                new OrderMenu(new MenuName("타파스"), new Count(1)),
                new OrderMenu(new MenuName("시저샐러드"), new Count(1)),
                new OrderMenu(new MenuName("크리스마스파스타"), new Count(2)),
                new OrderMenu(new MenuName("초코케이크"), new Count(1)),
                new OrderMenu(new MenuName("레드와인"), new Count(1))));
    }


    @Test
    void applyDiscountTest() {
        WeekendDiscount weekendDiscount = new WeekendDiscount();

        int discountPrice = weekendDiscount.apply(totalOrder);

        assertThat(discountPrice).isEqualTo(4046);
    }
}