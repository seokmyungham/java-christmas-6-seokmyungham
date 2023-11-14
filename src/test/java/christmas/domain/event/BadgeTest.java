package christmas.domain.event;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BadgeTest {

    @DisplayName("총혜택 금액에 따라 배지를 부여한다.")
    @ParameterizedTest
    @CsvSource({
            "1500, NONE",
            "5000, STAR",
            "12000, TREE",
            "20000, SANTA"
    })
    void getBadgeTest(int totalBenefits, Badge badge) {
        assertThat(Badge.getBadge(totalBenefits)).isEqualTo(badge);
    }
}