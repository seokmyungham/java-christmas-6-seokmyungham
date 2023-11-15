package christmas.domain.order;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import christmas.domain.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

        int orderMenuPrice = orderMenu.calculatePrice();

        assertThat(orderMenuPrice).isEqualTo(expectedPrice);
    }

    @DisplayName("equals 메서드 오버라이드 -> 동일한 객체를 비교할 시 참을 반환합니다")
    @Test
    void equalsObjTest() {
        OrderMenu orderMenu = new OrderMenu(new MenuName("양송이수프"), new Count(1));

        assertThat(orderMenu).isEqualTo(orderMenu);
    }

    @DisplayName("equals 메서드 오버라이드 -> 비교 대상이 OrderMenu의 인스턴스가 아니면 거짓을 반환합니다.")
    @Test
    void equalsNotInstanceofObjTest() {
        OrderMenu orderMenu = new OrderMenu(new MenuName("양송이수프"), new Count(1));

        assertThat(orderMenu.equals(new MenuName("양송이수프"))).isFalse();
    }

    @DisplayName("equals 메서드 오버라이드 -> 메뉴 이름이 같으면 같은 주문으로 판단합니다")
    @Test
    void equalsMenuNameTest() {
        OrderMenu orderMenu = new OrderMenu(new MenuName("양송이수프"), new Count(1));
        OrderMenu orderMenu2 = new OrderMenu(new MenuName("양송이수프"), new Count(2));

        assertThat(orderMenu).isEqualTo(orderMenu2);
    }
}