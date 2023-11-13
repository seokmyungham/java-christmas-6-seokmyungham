package christmas.domain.event.discount;

import christmas.domain.event.VisitDate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DdayDiscountTest {

    @DisplayName("이벤트 시작 일부터 방문 날짜까지 하루에 100원씩 할인 금액이 증가한다.")
    @ParameterizedTest
    @CsvSource({
            "1, 1000",
            "10, 1900",
            "25, 3400"
    })
    void applyTest(int visitDate, int discountPrice) {
        DdayDiscount ddayDiscount = new DdayDiscount();

        Assertions.assertThat(ddayDiscount.apply(new VisitDate(visitDate))).isEqualTo(discountPrice);
    }
}