package christmas.domain.order;

import christmas.domain.Menu;

public class MenuName {
    private final String menuName;

    public MenuName(String menuName) {
        validateMenuName(menuName);
        this.menuName = menuName;
    }

    private void validateMenuName(String menuName) {
        if (!Menu.containsName(menuName)) {
            throw new IllegalArgumentException("메뉴판에 없는 메뉴 이름입니다.");
        }
    }
}
