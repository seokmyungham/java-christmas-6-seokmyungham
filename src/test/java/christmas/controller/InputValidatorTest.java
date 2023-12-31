package christmas.controller;

import static christmas.constants.ErrorMessage.INVALID_DATE_ERROR_MESSAGE;
import static christmas.constants.ErrorMessage.INVALID_ORDER_ERROR_MESSAGE;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class InputValidatorTest {

    @DisplayName("입력이 정수가 아닐 경우 예외를 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"4a", "  ", "1.5", "10 "})
    void inputIsNotNumericExceptionTest(String input) {
        assertThatThrownBy(() -> new InputValidator().validateIsNumeric(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_DATE_ERROR_MESSAGE);

    }

    @DisplayName("입력이 정수가 맞을 경우 예외가 발생하지 않는다.")
    @ParameterizedTest
    @ValueSource(strings = {"0", "10"})
    void inputIsNumericNoExceptionTest(String input) {
        assertThatNoException()
                .isThrownBy(() -> new InputValidator().validateIsNumeric(input));
    }

    @DisplayName("주문 입력이 형식과 다를 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"양송이수프2", "제로콜라_2", "양송이수프-a"})
    void invalidInputFormatExceptionTest(String input) {
        assertThatThrownBy(() -> new InputValidator().validateInputFormat(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_ORDER_ERROR_MESSAGE);
    }

    @DisplayName("주문 입력이 형식과 같을 경우 예외가 발생하지 않는다.")
    @ParameterizedTest
    @ValueSource(strings = {"양송이수프-2", "제로콜라-2"})
    void validInputFormatNoExceptionTest(String input) {
        assertThatNoException()
                .isThrownBy(() -> new InputValidator().validateInputFormat(input));
    }
}