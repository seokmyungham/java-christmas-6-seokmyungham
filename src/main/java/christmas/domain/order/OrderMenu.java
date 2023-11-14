package christmas.domain.order;

import christmas.domain.Menu;
import christmas.domain.MenuType;
import java.util.Objects;

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

    public String getMenu() {
        return menu.getName();
    }

    public int getCount() {
        return count.getCount();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OrderMenu)) {
            return false;
        }
        OrderMenu orderMenu = (OrderMenu) obj;
        return Objects.equals(menu, orderMenu.menu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menu);
    }
}
