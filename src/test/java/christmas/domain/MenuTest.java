package christmas.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MenuTest {

    @DisplayName("주문한 메뉴 이름이 메뉴판에 존재할 경우 참을 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"양송이수프", "해산물파스타"})
    void returnTrueForExistingMenuName(String manuName) {
        Assertions.assertThat(Menu.containsName(manuName)).isTrue();
    }

    @DisplayName("주문한 메뉴 이름이 메뉴판에 존재하지 않을 경우 거짓을 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"사이다", "로제파스타"})
    void returnFalseForNonexistentMenuName(String manuName) {
        Assertions.assertThat(Menu.containsName(manuName)).isFalse();
    }
}