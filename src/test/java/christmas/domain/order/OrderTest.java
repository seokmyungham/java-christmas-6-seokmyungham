package christmas.domain.order;

import christmas.domain.Menu;
import java.util.List;
import org.assertj.core.api.Assertions;
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
    void getPriceTest(String menuName, int count) {
        Order totalOrder = new Order(List.of(new OrderMenu(new MenuName(menuName), new Count(count))));
        int expectedPrice = 0;
        expectedPrice += Menu.getMenuByName(menuName).getPrice() * count;

        int orderTotalPrice = totalOrder.getPrice();

        Assertions.assertThat(orderTotalPrice).isEqualTo(expectedPrice);
    }
}