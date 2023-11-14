package christmas.domain.event;

import christmas.domain.VisitDate;
import christmas.domain.order.Order;

public interface Event {
    int apply(Order order, VisitDate visitDate);

    boolean meetRequirements(Order order);

    EventType getType();
}
