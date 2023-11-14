package christmas.domain.order;

import static christmas.constants.ErrorMessage.INVALID_ORDER_ERROR_MESSAGE;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.MenuType;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderTest {
    private Order orders;

    @BeforeEach
    void setUp() {
        orders = new Order(List.of(
                new OrderMenu(new MenuName("타파스"), new Count(1)),
                new OrderMenu(new MenuName("시저샐러드"), new Count(1)),
                new OrderMenu(new MenuName("크리스마스파스타"), new Count(2)),
                new OrderMenu(new MenuName("초코케이크"), new Count(1)),
                new OrderMenu(new MenuName("레드와인"), new Count(1))));
    }

    @DisplayName("총 주문 금액을 반환한다.")
    @Test
    void getPriceTest() {
        assertThat(orders.totalPrice()).isEqualTo(138500);
    }

    @DisplayName("총 주문 내역에서 특정 메뉴 타입 주문 수를 반환한다.")
    @Test
    void countMenuTypeTest() {
        assertThat(orders.countMenuType(MenuType.MAIN)).isEqualTo(2);
    }

    @DisplayName("총 주문 내역에 중복된 메뉴 주문이 있으면 에러를 발생한다.")
    @Test
    void duplicatedOrderMenuExceptionTest() {
        assertThatThrownBy(() -> new Order(List.of(
                        new OrderMenu(new MenuName("타파스"), new Count(1)),
                        new OrderMenu(new MenuName("타파스"), new Count(2))))
                ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_ORDER_ERROR_MESSAGE);
    }
}