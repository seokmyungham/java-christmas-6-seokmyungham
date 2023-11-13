package christmas.domain.event.discount;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.event.Event;
import christmas.domain.event.VisitDate;
import christmas.domain.order.Order;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class StarDayDiscountTest {

    @DisplayName("이벤트 날짜에 방문할 경우 1000원을 할인한다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 24, 25, 31})
    void applyDiscountTest(int visitDate) {
        Event discountEvent = new StarDayDiscount();

        assertThat(discountEvent.apply(new Order(List.of()), new VisitDate(visitDate))).isEqualTo(1000);
    }

    @DisplayName("방문한 날짜가 이벤트 기간이 아닐 경우 0원을 반환한다.")
    @ParameterizedTest
    @ValueSource(ints = {5, 26})
    void notApplyDiscountTest(int visitDate) {
        Event discountEvent = new StarDayDiscount();

        assertThat(discountEvent.apply(new Order(List.of()), new VisitDate(visitDate))).isEqualTo(0);
    }

}