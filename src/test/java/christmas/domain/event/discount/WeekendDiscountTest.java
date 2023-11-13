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


    @DisplayName("주말에 방문할 경우 메인 메뉴 주문 1개당 2023원을 할인한다.")
    @Test
    void applyDiscountTest() {
        WeekendDiscount weekendDiscount = new WeekendDiscount();

        int discountPrice = weekendDiscount.apply(totalOrder, new VisitDate(9));

        assertThat(discountPrice).isEqualTo(4046);
    }

    @DisplayName("평일에 방문할 경우 이벤트가 적용되지 않고 0원을 반환한다.")
    @Test
    void notApplyDiscountTest() {
        WeekendDiscount weekendDiscount = new WeekendDiscount();

        int discountPrice = weekendDiscount.apply(totalOrder, new VisitDate(13));

        assertThat(discountPrice).isEqualTo(0);
    }
}