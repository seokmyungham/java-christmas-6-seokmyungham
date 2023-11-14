package christmas.domain.order;

import static christmas.constants.ErrorMessage.INVALID_ORDER_ERROR_MESSAGE;

import christmas.domain.MenuType;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Order {
    private final List<OrderMenu> orders;

    public Order(List<OrderMenu> orders) {
        validateDuplicateOrderMenu(orders);
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

    private void validateDuplicateOrderMenu(List<OrderMenu> orders) {
        Set<OrderMenu> dedupe = new HashSet<>(orders);

        if (orders.size() != dedupe.size()) {
            throw new IllegalArgumentException(INVALID_ORDER_ERROR_MESSAGE);
        }
    }
}
