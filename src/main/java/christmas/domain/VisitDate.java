package christmas.domain;

import static christmas.constants.ErrorMessage.INVALID_DATE_ERROR_MESSAGE;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class VisitDate {
    private static final int START_DATE = 1;
    private static final int FIRST_DAY_OF_MONTH = 1;
    private static final int LAST_DAY_OF_MONTH = 31;

    private final int visitDate;

    public VisitDate(int visitDate) {
        validateVisitDate(visitDate);
        this.visitDate = visitDate;
    }

    public int daysElapsed() {
        return visitDate - START_DATE;
    }

    public boolean isVisitInRange(int date) {
        return visitDate <= date;
    }

    public boolean isWeekend(int year, int month) {
        LocalDate localDate = LocalDate.of(year, month, visitDate);
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();

        return dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY;
    }

    public boolean isWeekday(int year, int month) {
        return !isWeekend(year, month);
    }

    public boolean isVisitInEventDay(List<Integer> eventDay) {
        return eventDay.contains(visitDate);
    }

    public int getVisitDate() {
        return visitDate;
    }

    private void validateVisitDate(int visitDate) {
        if (visitDate < FIRST_DAY_OF_MONTH || visitDate > LAST_DAY_OF_MONTH) {
            throw new IllegalArgumentException(INVALID_DATE_ERROR_MESSAGE);
        }
    }
}
