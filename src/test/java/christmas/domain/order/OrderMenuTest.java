package christmas.domain.order;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import christmas.domain.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class OrderMenuTest {

    @DisplayName("OrderMenu의 주문 가격 계산 테스트")
    @ParameterizedTest
    @CsvSource({
            "양송이수프, 2",
            "시저샐러드, 3",
            "해산물파스타, 4"
    })
    void getPriceTest(String menuName, int count) {
        OrderMenu orderMenu = new OrderMenu(new MenuName(menuName), new Count(count));
        int expectedPrice = Menu.getMenuByName(menuName).getPrice() * count;

        int orderMenuPrice = orderMenu.getPrice();

        assertThat(orderMenuPrice).isEqualTo(expectedPrice);
    }
}