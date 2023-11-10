package christmas.domain.order;

import java.util.List;

public class Order {
    private final List<OrderMenu> orders;

    public Order(List<OrderMenu> orders) {
        this.orders = orders;
    }
}
