package christmas.domain.event;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.VisitDate;
import christmas.domain.order.Count;
import christmas.domain.order.MenuName;
import christmas.domain.order.Order;
import christmas.domain.order.OrderMenu;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GiftEventTest {
    private Order totalOrder, totalOrder2;

    @BeforeEach
    void setUp() {
        totalOrder = new Order(List.of(
                new OrderMenu(new MenuName("타파스"), new Count(1)),
                new OrderMenu(new MenuName("시저샐러드"), new Count(1)),
                new OrderMenu(new MenuName("크리스마스파스타"), new Count(2)),
                new OrderMenu(new MenuName("초코케이크"), new Count(2)),
                new OrderMenu(new MenuName("레드와인"), new Count(1))));

        totalOrder2 = new Order(List.of(
                new OrderMenu(new MenuName("타파스"), new Count(1)),
                new OrderMenu(new MenuName("시저샐러드"), new Count(1)),
                new OrderMenu(new MenuName("크리스마스파스타"), new Count(2)),
                new OrderMenu(new MenuName("초코케이크"), new Count(2))));
    }

    @DisplayName("총 주문금액이 120,000 이상이면 샴페인 가격을 반환한다.")
    @Test
    void applyTest() {
        Event event = new GiftEvent();

        assertThat(event.apply(totalOrder, new VisitDate(25))).isEqualTo(25000);
    }

    @DisplayName("총 주문금액이 120,000 미만이면 샴페인 증정 없이 0원을 반환한다.")
    @Test
    void notApplyTest() {
        Event event = new GiftEvent();

        assertThat(event.apply(totalOrder2, new VisitDate(25))).isEqualTo(0);
    }
}