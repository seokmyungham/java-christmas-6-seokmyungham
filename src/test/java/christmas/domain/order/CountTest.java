package christmas.domain.order;

import static christmas.constants.ErrorMessage.INVALID_ORDER_ERROR_MESSAGE;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CountTest {

    @DisplayName("메뉴를 1개 이하로 주문할 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-5, 0})
    void invalidCountExceptionTest(int count) {
        assertThatThrownBy(() -> new Count(count))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_ORDER_ERROR_MESSAGE);
    }

    @DisplayName("메뉴를 1개 이상 주문할 경우 Count가 정상 생성된다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 5})
    void validCountNoExceptionTest(int count) {
        assertThatNoException()
                .isThrownBy(() -> new Count(count));
    }
}