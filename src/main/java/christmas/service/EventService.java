package christmas.service;

import christmas.domain.Menu;
import christmas.domain.event.Event;
import christmas.domain.event.EventType;
import christmas.domain.event.GiftEvent;
import christmas.domain.event.VisitDate;
import christmas.domain.event.discount.DDayDiscount;
import christmas.domain.event.discount.StarDayDiscount;
import christmas.domain.event.discount.WeekdayDiscount;
import christmas.domain.event.discount.WeekendDiscount;
import christmas.domain.order.Order;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventService {
    private final List<Event> events;

    public EventService() {
        this.events = new ArrayList<>();
        events.add(new DDayDiscount());
        events.add(new WeekendDiscount());
        events.add(new WeekdayDiscount());
        events.add(new StarDayDiscount());
        events.add(new GiftEvent());
    }

    public Map<EventType, Integer> manageEvents(Order order, VisitDate visitDate) {
        Map<EventType, Integer> eventBenefits = new HashMap<>();

        for (Event event : events) {
            eventBenefits.put(event.getType(), event.apply(order, visitDate));
        }

        return eventBenefits;
    }

    public int sumEventBenefits(Map<EventType, Integer> eventBenefits) {
        return -eventBenefits.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public Map<Menu, Integer> giftEvents(Order order) {
        return new GiftEvent().getGift(order);
    }
}
