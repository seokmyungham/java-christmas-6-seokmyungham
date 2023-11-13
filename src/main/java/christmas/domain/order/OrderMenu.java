package christmas.domain.order;

import christmas.domain.Menu;
import christmas.domain.MenuType;

public class OrderMenu {
    private final Menu menu;
    private final Count count;

    public OrderMenu(MenuName menuName, Count count) {
        this.menu = Menu.getMenuByName(menuName.getMenuName());
        this.count = count;
    }

    public int matchMenuTypeCount(MenuType menuType) {
        if (menu.equalsMenuType(menuType)) {
            return count.getCount();
        }

        return 0;
    }

    public int calculatePrice() {
        return menu.getPrice() * count.getCount();
    }
}
