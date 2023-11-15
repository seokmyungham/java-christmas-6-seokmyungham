package christmas.domain.order;

import static christmas.constants.ErrorMessage.INVALID_ORDER_ERROR_MESSAGE;

public class Count {
    private static final int MINIMUM_ALLOWED_COUNT = 1;

    private final int count;

    public Count(int count) {
        validateCount(count);
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    private void validateCount(int count) {
        if (count < MINIMUM_ALLOWED_COUNT) {
            throw new IllegalArgumentException(INVALID_ORDER_ERROR_MESSAGE);
        }
    }
}
