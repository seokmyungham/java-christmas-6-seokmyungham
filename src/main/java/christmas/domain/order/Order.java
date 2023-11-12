package christmas.domain.order;

import java.util.List;

public class Order {
    private final List<OrderMenu> totalOrder;

    public Order(List<OrderMenu> orders) {
        this.totalOrder = orders;
    }

    public int getOrderTotalPrice() {
        return totalOrder.stream()
                .mapToInt(OrderMenu::getOrderMenuPrice)
                .sum();
    }
}