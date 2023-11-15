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
import org.junit.jupiter.params.provider.CsvSource;

class DDayDiscountTest {
    private Order totalOrder;

    @BeforeEach
    void setUp() {
        totalOrder = new Order(List.of(new OrderMenu(new MenuName("티본스테이크"), new Count(1))));
    }

    /*
    크리스마스 D-DAY 이벤트 적용 조건
    총 주문금액: 10,000원 이상
    1~25일까지 이벤트 진행
    */

    @DisplayName("이벤트 조건을 만족할 경우, 시작 일부터 방문 날짜까지 하루에 100원씩 할인 금액이 증가한다.")
    @ParameterizedTest
    @CsvSource({
            "1, 1000",
            "10, 1900",
            "25, 3400"
    })
    void applyDiscountTest(int visitDate, int discountPrice) {
        Event dDayDiscount = new DDayDiscount();

        assertThat(dDayDiscount.apply(totalOrder, new VisitDate(visitDate))).isEqualTo(discountPrice);
    }

    @DisplayName("모든 조건을 만족할 시 참을 반환한다")
    @Test
    void meetRequirementsTrueTest() {
        Event dDayDiscount = new DDayDiscount();

        assertThat(dDayDiscount.meetRequirements(totalOrder, new VisitDate(25))).isTrue();
    }

    @DisplayName("총 주문금액이 10,000원 이상이지만, 이벤트 기한이 아닐 경우 거짓을 반환한다.")
    @Test
    void meetRequirementsFalseCase1Test() {
        Event dDayDiscount = new DDayDiscount();

        assertThat(dDayDiscount.apply(totalOrder, new VisitDate(30))).isEqualTo(0);
    }

    @DisplayName("이벤트 기간에 방문했지만, 총 주문금액이 10,000원 미만일 경우 거짓을 반환한다.")
    @Test
    void meetRequirementsFalseCase2Test() {
        Order orders = new Order(List.of(new OrderMenu(new MenuName("양송이수프"), new Count(1))));

        Event dDayDiscount = new DDayDiscount();

        assertThat(dDayDiscount.apply(orders, new VisitDate(30))).isEqualTo(0);
    }

    @DisplayName("모든 조건을 만족하지 못했을 경우 거짓을 반환한다.")
    @Test
    void meetRequirementsFalseCase3Test() {
        Order orders = new Order(List.of(new OrderMenu(new MenuName("양송이수프"), new Count(1))));

        Event dDayDiscount = new DDayDiscount();

        assertThat(dDayDiscount.meetRequirements(orders, new VisitDate(30))).isFalse();
    }
}