package christmas.controller;

import static christmas.constants.ErrorMessage.INVALID_DATE_ERROR_MESSAGE;

public class InputValidator {
    private static final String DELIMITER = ",";

    public void validateIsNumeric(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_DATE_ERROR_MESSAGE);
        }
    }
}
