package christmas.domain.order;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import christmas.domain.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class OrderMenuTest {

    @DisplayName("메뉴 이름과 메뉴 개수로 OrderMenu를 생성해서 메뉴 주문 가격을 반환한다.")
    @ParameterizedTest
    @CsvSource({
            "양송이수프, 2",
            "시저샐러드, 3",
            "해산물파스타, 4"
    })
    void getOrderMenuPriceTest(String menu, int count) {
        OrderMenu orderMenu = new OrderMenu(new MenuName(menu), new Count(count));

        int orderMenuPrice = orderMenu.getOrderMenuPrice();

        assertThat(orderMenuPrice).isEqualTo(Menu.getPriceByName(menu) * count);
    }
}