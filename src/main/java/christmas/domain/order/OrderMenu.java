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

    public MenuType getOrderMenuType() {
        return menu.getMenuType();
    }

    public int getOrderMenuPrice() {
        return menu.getPrice() * count.getCount();
    }
}
