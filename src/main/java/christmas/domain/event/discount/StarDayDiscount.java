package christmas.domain.event.discount;

public class StarDayDiscount {
    private static final int discountPrice = 1000;

    public int apply() {
        return discountPrice;
    }
}
