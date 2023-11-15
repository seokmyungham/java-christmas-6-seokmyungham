package christmas;

import christmas.controller.EventController;
import christmas.controller.OrderController;
import christmas.domain.VisitDate;
import christmas.domain.order.Order;

public class Application {
    public static void main(String[] args) {
        OrderController orderController = new OrderController();
        EventController eventController = new EventController();

        VisitDate visitDate = orderController.processGetVisitDate();
        Order order = orderController.processGetOrder();

        eventController.processEvents(order, visitDate);
    }
}
