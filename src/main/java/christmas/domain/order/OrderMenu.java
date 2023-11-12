package christmas.domain.order;

import christmas.domain.Menu;

public class OrderMenu {
    private final MenuName menuName;
    private final Count count;

    public OrderMenu(MenuName menuName, Count count) {
        this.menuName = menuName;
        this.count = count;
    }

    public int getOrderMenuPrice() {
        int price = Menu.getPriceByName(menuName.getMenuName());
        int count = this.count.getCount();

        return price * count;
    }
}
