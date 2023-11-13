package christmas.domain.event.discount;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.event.VisitDate;
import christmas.domain.order.Count;
import christmas.domain.order.MenuName;
import christmas.domain.order.Order;
import christmas.domain.order.OrderMenu;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WeekdayDiscountTest {
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


    @DisplayName("평일에 방문할 경우 디저트 메뉴 주문 1개당 2023원을 할인한다.")
    @Test
    void applyDiscountTest() {
        WeekdayDiscount weekdayDiscount = new WeekdayDiscount();

        int discountPrice = weekdayDiscount.apply(totalOrder, new VisitDate(6));

        assertThat(discountPrice).isEqualTo(2023);
    }

    @DisplayName("주말에 방문할 경우 이벤트가 적용되지 않고 0원을 반환한다.")
    @Test
    void notApplyDiscountTest() {
        WeekdayDiscount weekdayDiscount = new WeekdayDiscount();

        int discountPrice = weekdayDiscount.apply(totalOrder, new VisitDate(8));

        assertThat(discountPrice).isEqualTo(0);
    }
}