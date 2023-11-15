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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class StarDayDiscountTest {
    private Order totalOrder;

    @BeforeEach
    void setUp() {
        totalOrder = new Order(List.of(new OrderMenu(new MenuName("티본스테이크"), new Count(1))));
    }

    /*
    StarDay 이벤트 적용 조건
    총 주문금액: 10,000원 이상
    이벤트 날짜에 방문
     */

    @DisplayName("이벤트 적용 조건을 만족할 경우 1000원을 반환한다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 24, 25, 31})
    void applyDiscountTest(int visitDate) {
        Event starDayDiscount = new StarDayDiscount();

        assertThat(starDayDiscount.apply(totalOrder, new VisitDate(visitDate))).isEqualTo(1000);
    }

    @DisplayName("이벤트 적용 조건을 만족하지 못할 경우 0원을 반환한다.")
    @ParameterizedTest
    @ValueSource(ints = {5, 26})
    void notApplyDiscountTest(int visitDate) {
        Event starDayDiscount = new StarDayDiscount();

        assertThat(starDayDiscount.apply(totalOrder, new VisitDate(visitDate))).isEqualTo(0);
    }

    @DisplayName("모든 조건을 만족할 시 참을 반환한다")
    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 24, 25, 31})
    void meetRequirementsTrueTest(int visitDate) {
        Event starDayDiscount = new StarDayDiscount();

        assertThat(starDayDiscount.meetRequirements(totalOrder, new VisitDate(visitDate))).isTrue();
    }

    @DisplayName("총 주문금액이 10,000원 이상이지만, 방문 날짜가 이벤트 날짜가 아닐 시 거짓을 반환한다")
    @ParameterizedTest
    @ValueSource(ints = {5, 26})
    void meetRequirementsFalseCase1Test(int visitDate) {
        Event starDayDiscount = new StarDayDiscount();

        assertThat(starDayDiscount.meetRequirements(totalOrder, new VisitDate(visitDate))).isFalse();
    }

    @DisplayName("이벤트 날짜에 방문했지만 총 주문금액이 10,000원 미만일 시 거짓을 반환한다")
    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 24, 25, 31})
    void meetRequirementsFalseCase2Test(int visitDate) {
        Order orders = new Order(List.of(new OrderMenu(new MenuName("양송이수프"), new Count(1))));

        Event starDayDiscount = new StarDayDiscount();

        assertThat(starDayDiscount.meetRequirements(orders, new VisitDate(visitDate))).isFalse();
    }

    @DisplayName("모든 조건을 만족하지 못했을 경우 거짓을 반환한다.")
    @ParameterizedTest
    @ValueSource(ints = {5, 26})
    void meetRequirementsFalseCase3Test(int visitDate) {
        Order orders = new Order(List.of(new OrderMenu(new MenuName("양송이수프"), new Count(1))));

        Event starDayDiscount = new StarDayDiscount();

        assertThat(starDayDiscount.meetRequirements(orders, new VisitDate(visitDate))).isFalse();
    }
}