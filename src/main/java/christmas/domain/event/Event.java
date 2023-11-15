package christmas.domain.event;

import christmas.domain.VisitDate;
import christmas.domain.order.Order;

public interface Event {
    int EVENT_REQUIREMENT = 10000;

    int apply(Order order, VisitDate visitDate);

    boolean meetRequirements(Order order);

    EventType getType();
}
