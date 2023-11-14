package christmas.domain.order;

import static christmas.constants.ErrorMessage.INVALID_ORDER_ERROR_MESSAGE;

import christmas.domain.MenuType;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Order {
    private static final int MAXIMUM_ORDER_COUNT = 20;

    private final List<OrderMenu> orders;

    public Order(List<OrderMenu> orders) {
        validateDuplicateOrderMenu(orders);
        validateOrderMenuCount(orders);
        this.orders = Collections.unmodifiableList(orders);
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

    public List<OrderMenu> getOrders() {
        return orders;
    }

    private void validateDuplicateOrderMenu(List<OrderMenu> orders) {
        Set<OrderMenu> dedupe = new HashSet<>(orders);

        if (orders.size() != dedupe.size()) {
            throw new IllegalArgumentException(INVALID_ORDER_ERROR_MESSAGE);
        }
    }

    private void validateOrderMenuCount(List<OrderMenu> orders) {
        if (calculateTotalCount(orders) > MAXIMUM_ORDER_COUNT) {
            throw new IllegalArgumentException(INVALID_ORDER_ERROR_MESSAGE);
        }
    }

    private int calculateTotalCount(List<OrderMenu> orders) {
        return orders.stream()
                .mapToInt(OrderMenu::getCount)
                .sum();
    }
}
