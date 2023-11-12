package christmas.domain.order;

import christmas.domain.MenuType;
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

    public int countMenuType(MenuType menuType) {
        return totalOrder.stream()
                .filter(orderMenu -> orderMenu.getOrderMenuType().equals(menuType))
                .mapToInt(OrderMenu::getCount)
                .sum();
    }

}
