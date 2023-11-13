package christmas.domain.event.discount;

import christmas.domain.event.VisitDate;

public class ChristmasDiscount {
    private static final int STARTING_PRICE = 1000;
    private static final int DAILY_PRICE = 100;

    public int apply(VisitDate visitDate) {
        return STARTING_PRICE + visitDate.daysElapsed() * DAILY_PRICE;
    }
}
