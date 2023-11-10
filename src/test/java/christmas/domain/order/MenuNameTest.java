package christmas.domain.order;

import static christmas.constants.ErrorMessage.INVALID_ORDER_ERROR_MESSAGE;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MenuNameTest {

    @DisplayName("메뉴판에 없는 메뉴 이름을 입력할 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"사이다", "로제파스타"})
    void invalidMenuNameExceptionTest(String manuName) {
        assertThatThrownBy(() -> new MenuName(manuName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_ORDER_ERROR_MESSAGE);
    }

    @DisplayName("주문한 메뉴가 메뉴판에 있을 경우 MenuName이 정상 생성된다.")
    @ParameterizedTest
    @ValueSource(strings = {"양송이수프", "해산물파스타"})
    void validMenuNameNoExceptionTest(String manuName) {
        assertThatNoException()
                .isThrownBy(() -> new MenuName(manuName));
    }
}