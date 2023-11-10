package christmas.domain.order;

import static christmas.constants.ErrorMessage.INVALID_ORDER_ERROR_MESSAGE;

public class Count {
    private final int count;

    public Count(int count) {
        validateCount(count);
        this.count = count;
    }

    private void validateCount(int count) {
        if (count < 1) {
            throw new IllegalArgumentException(INVALID_ORDER_ERROR_MESSAGE);
        }
    }
}
