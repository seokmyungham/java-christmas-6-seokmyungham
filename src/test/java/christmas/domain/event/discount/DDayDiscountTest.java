package christmas.domain.event.discount;

import christmas.domain.event.Event;
import christmas.domain.event.VisitDate;
import christmas.domain.order.Order;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DDayDiscountTest {

    @DisplayName("이벤트 시작 일부터 방문 날짜까지 하루에 100원씩 할인 금액이 증가한다.")
    @ParameterizedTest
    @CsvSource({
            "1, 1000",
            "10, 1900",
            "25, 3400"
    })
    void applyDiscountTest(int visitDate, int discountPrice) {
        Event discountEvent = new DDayDiscount();

        Assertions.assertThat(discountEvent.apply(new Order(List.of()), new VisitDate(visitDate)))
                .isEqualTo(discountPrice);
    }

    @DisplayName("이벤트 기한이 아닐 경우 0원을 반환한다.")
    @Test
    void notApplyDisCountTest() {
        Event discountEvent = new DDayDiscount();

        Assertions.assertThat(discountEvent.apply(new Order(List.of()), new VisitDate(30)))
                .isEqualTo(0);
    }
}