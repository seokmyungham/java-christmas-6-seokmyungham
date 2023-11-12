package christmas.domain.order;

import java.util.List;

public class Order {
    private final List<OrderMenu> totalOrder;

    public Order(List<OrderMenu> orders) {
        this.totalOrder = orders;
    }

    public int getPrice() {
        return totalOrder.stream()
                .mapToInt(OrderMenu::getPrice)
                .sum();
    }
}
