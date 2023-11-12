package christmas.domain.order;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import christmas.domain.Menu;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class OrderTest {

    @DisplayName("총 주문 금액을 반환한다.")
    @ParameterizedTest
    @CsvSource({
            "타파스, 1",
            "시저샐러드, 1",
            "크리스마스파스타, 2",
            "초코케이크, 1",
            "레드와인, 2"
    })
    void getOrderMenuPriceTest(String menu, int count) {
        Order totalOrder = new Order(List.of(new OrderMenu(new MenuName(menu), new Count(count))));

        int orderTotalPrice = totalOrder.getOrderTotalPrice();
        int expectedValue = 0;
        expectedValue += Menu.getPriceByName(menu) * count;

        assertThat(orderTotalPrice).isEqualTo(expectedValue);
    }
}