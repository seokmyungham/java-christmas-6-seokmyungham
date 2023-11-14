package christmas.domain.event.discount;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.VisitDate;
import christmas.domain.event.Event;
import christmas.domain.order.Count;
import christmas.domain.order.MenuName;
import christmas.domain.order.Order;
import christmas.domain.order.OrderMenu;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class StarDayDiscountTest {
    private Order totalOrder;

    @BeforeEach
    void setUp() {
        totalOrder = new Order(List.of(new OrderMenu(new MenuName("티본스테이크"), new Count(1))));
    }

    @DisplayName("이벤트 날짜에 방문할 경우 1000원을 할인한다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 24, 25, 31})
    void applyDiscountTest(int visitDate) {
        Event starDayDiscount = new StarDayDiscount();

        assertThat(starDayDiscount.apply(totalOrder, new VisitDate(visitDate))).isEqualTo(1000);
    }

    @DisplayName("방문한 날짜가 이벤트 기간이 아닐 경우 0원을 반환한다.")
    @ParameterizedTest
    @ValueSource(ints = {5, 26})
    void notApplyDiscountTest(int visitDate) {
        Event starDayDiscount = new StarDayDiscount();

        assertThat(starDayDiscount.apply(totalOrder, new VisitDate(visitDate))).isEqualTo(0);
    }

    @DisplayName("이벤트 적용 조건을 만족하면 참을 반환한다.")
    @Test
    void meetRequirementsTrueTest() {
        Event starDayDiscount = new StarDayDiscount();

        assertThat(starDayDiscount.meetRequirements(totalOrder)).isTrue();
    }

    @DisplayName("이벤트 적용 조건을 만족하지 못하면 거짓을 반환한다.")
    @Test
    void meetRequirementsFalseTest() {
        Order orders = new Order(List.of(new OrderMenu(new MenuName("양송이수프"), new Count(1))));

        Event starDayDiscount = new StarDayDiscount();
        assertThat(starDayDiscount.meetRequirements(orders)).isFalse();
    }
}