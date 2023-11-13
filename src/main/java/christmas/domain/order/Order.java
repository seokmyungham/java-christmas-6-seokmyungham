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
                .mapToInt(OrderMenu::getPrice)
                .sum();
    }

    public int countMenuType(MenuType menuType) {
        return orders.stream()
                .filter(orderMenu -> orderMenu.getOrderMenuType().equals(menuType))
                .mapToInt(OrderMenu::getCount)
                .sum();
    }

}
