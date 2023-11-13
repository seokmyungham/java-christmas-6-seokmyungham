package christmas.domain.event;

import christmas.domain.order.Order;

public interface Event {
    int apply(Order order, VisitDate visitDate);
    EventType getType();
}
