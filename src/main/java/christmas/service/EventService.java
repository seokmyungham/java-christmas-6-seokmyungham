package christmas.service;

import static christmas.domain.event.EventType.CHRISTMAS_D_DAY_DISCOUNT;
import static christmas.domain.event.EventType.GIFT_EVENT;
import static christmas.domain.event.EventType.STAR_DAY_DISCOUNT;
import static christmas.domain.event.EventType.WEEKDAY_DISCOUNT;
import static christmas.domain.event.EventType.WEEKEND_DISCOUNT;

import christmas.domain.event.EventType;
import christmas.domain.event.GiftEvent;
import christmas.domain.event.VisitDate;
import christmas.domain.event.discount.DdayDiscount;
import christmas.domain.event.discount.StarDayDiscount;
import christmas.domain.event.discount.WeekdayDiscount;
import christmas.domain.event.discount.WeekendDiscount;
import christmas.domain.order.Order;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventService {
    private static final int CHRISTMAS_D_DAY = 25;
    private static final int YEAR = 2023, MONTH = 12;
    private static final List<Integer> STAR_DAY = new ArrayList<>(List.of(3, 10, 17, 24, 25, 31));

    public Map<EventType, Integer> manageBenefits(Order order, VisitDate visitDate) {
        Map<EventType, Integer> eventBenefits = new HashMap<>();
        eventBenefits.put(CHRISTMAS_D_DAY_DISCOUNT, dDayApply(visitDate));
        eventBenefits.put(WEEKEND_DISCOUNT, weekendApply(order, visitDate));
        eventBenefits.put(WEEKDAY_DISCOUNT, weekdayApply(order, visitDate));
        eventBenefits.put(STAR_DAY_DISCOUNT, starDayApply(visitDate));
        eventBenefits.put(GIFT_EVENT, giftApply(order));

        return eventBenefits;
    }

    private int dDayApply(VisitDate visitDate) {
        if (visitDate.isVisitInRange(CHRISTMAS_D_DAY)) {
            return new DdayDiscount().apply(visitDate);
        }
        return 0;
    }

    private int weekendApply(Order order, VisitDate visitDate) {
        if (visitDate.isWeekend(YEAR, MONTH)) {
            return new WeekendDiscount().apply(order);
        }
        return 0;
    }

    private int weekdayApply(Order order, VisitDate visitDate) {
        if (visitDate.isWeekday(YEAR, MONTH)) {
            return new WeekdayDiscount().apply(order);
        }
        return 0;
    }

    private int starDayApply(VisitDate visitDate) {
        if (visitDate.isVisitInEventDay(STAR_DAY)) {
            return new StarDayDiscount().apply();
        }
        return 0;
    }

    private int giftApply(Order order) {
        if (order.totalPrice() >= 120000) {
            return new GiftEvent().apply();
        }
        return 0;
    }
}
