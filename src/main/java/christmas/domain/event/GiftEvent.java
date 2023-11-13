package christmas.domain.event;

import static christmas.domain.Menu.CHAMPAGNE;

import christmas.domain.Menu;

public class GiftEvent {
    private static final Menu GIFT = CHAMPAGNE;

    public int apply() {
        return GIFT.getPrice();
    }
}
