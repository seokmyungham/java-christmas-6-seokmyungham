package christmas.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InputParserTest {

    @DisplayName("문자열과 Comma 구분자를 전달 받아 split을 수행합니다")
    @Test
    void parseInputByDelimiterCommaTest() {
        assertThat(InputParser.parseInputByDelimiter("타파스-2,레드와인-1", ","))
                .isEqualTo(new ArrayList<>(List.of("타파스-2", "레드와인-1")));

    }


    @DisplayName("문자열과 Hyphen 구분자를 전달 받아 split을 수행합니다")
    @Test
    void parseInputByDelimiterHyphenTest() {
        assertThat(InputParser.parseInputByDelimiter("타파스-2", "-"))
                .isEqualTo(new ArrayList<>(List.of("타파스", "2")));

    }
}