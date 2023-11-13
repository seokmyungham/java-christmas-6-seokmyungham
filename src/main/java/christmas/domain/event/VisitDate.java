package christmas.domain.event;

import static christmas.constants.ErrorMessage.INVALID_DATE_ERROR_MESSAGE;

public class VisitDate {
    private static final int START_DATE = 1;

    private final int visitDate;

    public VisitDate(int visitDate) {
        validateVisitDate(visitDate);
        this.visitDate = visitDate;
    }

    public int daysElapsed() {
        return visitDate - START_DATE;
    }

    private void validateVisitDate(int visitDate) {
        if (visitDate < 1 || visitDate > 31) {
            throw new IllegalArgumentException(INVALID_DATE_ERROR_MESSAGE);
        }
    }
}
