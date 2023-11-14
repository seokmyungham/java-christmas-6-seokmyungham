package christmas.controller;

import java.util.ArrayList;
import java.util.List;

public class InputParser {
    private static final String DELIMITER_COMMA = ",";
    private static final String DELIMITER_HYPHEN = "-";

    public static List<String> parseInputByComma(String input) {
        return new ArrayList<>(List.of(input.split(DELIMITER_COMMA)));
    }

    public static List<String> parseInputByHYPHEN(String input) {
        return new ArrayList<>(List.of(input.split(DELIMITER_HYPHEN)));
    }
}
