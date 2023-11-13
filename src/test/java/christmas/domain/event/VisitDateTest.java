package christmas.domain.event;

import static christmas.constants.ErrorMessage.INVALID_DATE_ERROR_MESSAGE;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class VisitDateTest {

    @DisplayName("방문 날짜가 1일에서 31일 사이의 숫자가 아닐 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 32})
    void invalidVisitDateExceptionTest(int visitDate) {
        assertThatThrownBy(() -> new VisitDate(visitDate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_DATE_ERROR_MESSAGE);
    }

    @DisplayName("방문 날짜가 1일과 31일 사이의 숫자일 경우 VisitDate가 정상 생성된다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 25, 31})
    void validVisitDateNoExceptionTest(int visitDate) {
        assertThatNoException()
                .isThrownBy(() -> new VisitDate(visitDate));
    }
}