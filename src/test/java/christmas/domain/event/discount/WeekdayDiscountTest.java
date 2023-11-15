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

    /*
    WeekDay 이벤트 적용 조건
    총 주문금액: 10,000원 이상
    평일에 방문 (일,월,화,수,목)
    */

    @DisplayName("이벤트 적용 조건을 만족할 경우 디저트 메뉴 주문 1개당 2023원을 반환한다.")
    @Test
    void applyDiscountTest() {
        Event weekdayDiscount = new WeekdayDiscount();

        int discountPrice = weekdayDiscount.apply(totalOrder, new VisitDate(6));

        assertThat(discountPrice).isEqualTo(2023);
    }

    @DisplayName("이벤트 적용 조건을 만족하지 못할 경우 이벤트가 적용되지 않고 0원을 반환한다.")
    @Test
    void notApplyDiscountTest() {
        Event weekdayDiscount = new WeekdayDiscount();

        int discountPrice = weekdayDiscount.apply(totalOrder, new VisitDate(8));

        assertThat(discountPrice).isEqualTo(0);
    }

    @DisplayName("모든 조건을 만족할 시 참을 반환한다")
    @Test
    void meetRequirementsTrueTest() {
        Event weekdayDiscount = new WeekdayDiscount();

        assertThat(weekdayDiscount.meetRequirements(totalOrder, new VisitDate(6))).isTrue();
    }

    @DisplayName("총 주문금액이 10,000원 이상이지만, 주말에 방문했을 시 거짓을 반환한다")
    @Test
    void meetRequirementsFalseCase1Test() {
        Event weekdayDiscount = new WeekdayDiscount();

        assertThat(weekdayDiscount.meetRequirements(totalOrder, new VisitDate(8))).isFalse();
    }

    @DisplayName("평일에 방문했지만, 총 주문금액이 10,000원 미만일 시 거짓을 반환한다")
    @Test
    void meetRequirementsFalseCase2Test() {
        Order orders = new Order(List.of(new OrderMenu(new MenuName("양송이수프"), new Count(1))));

        Event weekdayDiscount = new WeekdayDiscount();

        assertThat(weekdayDiscount.meetRequirements(orders, new VisitDate(6))).isFalse();
    }

    @DisplayName("모든 조건을 만족하지 못했을 경우 거짓을 반환한다.")
    @Test
    void meetRequirementsFalseCase3Test() {
        Order orders = new Order(List.of(new OrderMenu(new MenuName("양송이수프"), new Count(1))));

        Event weekdayDiscount = new WeekdayDiscount();

        assertThat(weekdayDiscount.meetRequirements(orders, new VisitDate(8))).isFalse();
    }
}