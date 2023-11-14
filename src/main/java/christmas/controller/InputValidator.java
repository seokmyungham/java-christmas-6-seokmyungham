package christmas.controller;

import static christmas.constants.ErrorMessage.INVALID_DATE_ERROR_MESSAGE;
import static christmas.constants.ErrorMessage.INVALID_ORDER_ERROR_MESSAGE;

public class InputValidator {
    private static final String DELIMITER = ",";
    private static final String ORDER_FORMAT = "^[가-힣]+-\\d+(,[가-힣]+-\\d+)*$";

    public void validateIsNumeric(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_DATE_ERROR_MESSAGE);
        }
    }

    public void validateInputFormat(String input) {
        if (!input.matches(ORDER_FORMAT)) {
            throw new IllegalArgumentException(INVALID_ORDER_ERROR_MESSAGE);
        }
    }
}
