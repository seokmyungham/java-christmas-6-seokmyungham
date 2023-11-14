package christmas.domain.event.discount;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.event.Event;
import christmas.domain.event.VisitDate;
import christmas.domain.order.Count;
import christmas.domain.order.MenuName;
import christmas.domain.order.Order;
import christmas.domain.order.OrderMenu;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DDayDiscountTest {
    private Order totalOrder;

    @BeforeEach
    void setUp() {
        totalOrder = new Order(List.of(new OrderMenu(new MenuName("티본스테이크"), new Count(1))));
    }

    @DisplayName("이벤트 시작 일부터 방문 날짜까지 하루에 100원씩 할인 금액이 증가한다.")
    @ParameterizedTest
    @CsvSource({
            "1, 1000",
            "10, 1900",
            "25, 3400"
    })
    void applyDiscountTest(int visitDate, int discountPrice) {
        Event dDayDiscount = new DDayDiscount();

        assertThat(dDayDiscount.apply(totalOrder, new VisitDate(visitDate)))
                .isEqualTo(discountPrice);
    }

    @DisplayName("이벤트 기한이 아닐 경우 0원을 반환한다.")
    @Test
    void notApplyDisCountTest() {
        Event dDayDiscount = new DDayDiscount();

        assertThat(dDayDiscount.apply(totalOrder, new VisitDate(30)))
                .isEqualTo(0);
    }

    @DisplayName("이벤트 적용 조건을 만족하면 참을 반환한다.")
    @Test
    void meetRequirementsTrueTest() {
        Event dDayDiscount = new DDayDiscount();

        assertThat(dDayDiscount.meetRequirements(totalOrder)).isTrue();
    }

    @DisplayName("이벤트 적용 조건을 만족하지 못하면 거짓을 반환한다.")
    @Test
    void meetRequirementsFalseTest() {
        Order orders = new Order(List.of(new OrderMenu(new MenuName("양송이수프"), new Count(1))));

        Event dDayDiscount = new DDayDiscount();
        assertThat(dDayDiscount.meetRequirements(orders)).isFalse();
    }
}