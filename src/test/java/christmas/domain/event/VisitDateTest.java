package christmas.domain.event;

import static christmas.constants.ErrorMessage.INVALID_DATE_ERROR_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.VisitDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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

    @DisplayName("방문 날짜가 이벤트 시작 날짜로부터 몇 일이 경과되었는지 계산한다.")
    @ParameterizedTest
    @CsvSource({
            "1, 0",
            "10, 9",
    })
    void daysElapsedTest(int visitDate, int startDate) {
        assertThat(new VisitDate(visitDate).daysElapsed()).isEqualTo(startDate);
    }

    @DisplayName("이벤트 기한 전에 방문할 경우 참을 반환한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 25})
    void isVisitInRangeTrueTest(int visitDate) {
        assertThat(new VisitDate(visitDate).isVisitInRange(25)).isTrue();
    }

    @DisplayName("이벤트 기한 후에 방문할 경우 거짓을 반환한다.")
    @Test
    void isVisitInRangeFalseTest() {
        assertThat(new VisitDate(26).isVisitInRange(25)).isFalse();
    }

    @DisplayName("방문 날짜가 주말(금,토요일)인지 판단한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 8, 9, 15, 16, 22, 23, 29, 30})
    void isWeekendTest(int weekend) {
        assertThat(new VisitDate(weekend).isWeekend(2023, 12)).isTrue();
    }

    @DisplayName("방문 날짜가 주중인지 판단한다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 6, 25, 31})
    void isWeekdayTest(int weekday) {
        assertThat(new VisitDate(weekday).isWeekday(2023, 12)).isTrue();
    }

    @DisplayName("이벤트를 진행하는 날에 방문하면 참을 반환한다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 25})
    void isVisitInEventDayTrueTest(int visitDate) {
        List<Integer> starDay = new ArrayList<>(List.of(3, 10, 17, 24, 25, 31));

        assertThat(new VisitDate(visitDate).isVisitInEventDay(starDay)).isTrue();
    }

    @DisplayName("이벤트를 진행하는 날에 방문한게 아니면 거짓을 반환한다.")
    @ParameterizedTest
    @ValueSource(ints = {4, 30})
    void isVisitInEventDayFalseTest(int visitDate) {
        List<Integer> starDay = new ArrayList<>(List.of(3, 10, 17, 24, 25, 31));

        assertThat(new VisitDate(visitDate).isVisitInEventDay(starDay)).isFalse();
    }
}