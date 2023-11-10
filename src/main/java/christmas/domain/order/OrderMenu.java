package christmas.domain.order;

public class OrderMenu {
    private final MenuName menuName;
    private final Count count;

    public OrderMenu(MenuName menuName, Count count) {
        this.menuName = menuName;
        this.count = count;
    }
}
