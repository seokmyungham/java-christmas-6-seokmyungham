package christmas.domain.order;

import christmas.domain.MenuType;
import java.util.List;

public class Order {
    private final List<OrderMenu> orders;

    public Order(List<OrderMenu> orders) {
        this.orders = orders;
    }

    public int totalPrice() {
        return orders.stream()
                .mapToInt(OrderMenu::calculatePrice)
                .sum();
    }

    public int countMenuType(MenuType menuType) {
        return orders.stream()
                .mapToInt(orderMenu -> orderMenu.matchMenuTypeCount(menuType))
                .sum();
    }
}
