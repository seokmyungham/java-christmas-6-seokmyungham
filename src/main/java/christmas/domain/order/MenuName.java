package christmas.domain.order;

import static christmas.constants.ErrorMessage.INVALID_ORDER_ERROR_MESSAGE;

import christmas.domain.Menu;

public class MenuName {
    private final String menuName;

    public MenuName(String menuName) {
        validateMenuName(menuName);
        this.menuName = menuName;
    }

    private void validateMenuName(String menuName) {
        if (!Menu.containsName(menuName)) {
            throw new IllegalArgumentException(INVALID_ORDER_ERROR_MESSAGE);
        }
    }
}
