package christmas.domain;

import static christmas.constants.ErrorMessage.INVALID_ORDER_ERROR_MESSAGE;
import static christmas.domain.MenuType.APPETIZER;
import static christmas.domain.MenuType.DESSERT;
import static christmas.domain.MenuType.DRINK;
import static christmas.domain.MenuType.MAIN;

import java.util.Arrays;

public enum Menu {
    BUTTON_MUSHROOM_SOUP(APPETIZER, "양송이수프", 6000),
    TAPAS(APPETIZER, "타파스", 5500),
    CAESAR_SALAD(APPETIZER, "시저샐러드", 8000),

    T_BONE_STEAK(MAIN, "티본스테이크", 55000),
    BARBECUE_RIBS(MAIN, "바비큐립", 54000),
    SEAFOOD_PASTA(MAIN, "해산물파스타", 35000),
    CHRISTMAS_PASTA(MAIN, "크리스마스파스타", 25000),

    CHOCOLATE_CAKE(DESSERT, "초코케이크", 15000),
    ICE_CREAM(DESSERT, "아이스크림", 5000),

    ZERO_COKE(DRINK, "제로콜라", 3000),
    RED_WINE(DRINK, "레드와인", 60000),
    CHAMPAGNE(DRINK, "샴페인", 25000);

    private final MenuType menuType;
    private final String name;
    private final int price;

    Menu(MenuType menuType, String name, int price) {
        this.menuType = menuType;
        this.name = name;
        this.price = price;
    }

    public static boolean containsName(String name) {
        return Arrays.stream(values())
                .anyMatch(menu -> menu.getName().equals(name));
    }

    public static Menu getMenuByName(String name) {
        return Arrays.stream(values())
                .filter(menu -> menu.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_ORDER_ERROR_MESSAGE));
    }

    public static int getPriceByName(String name) {
        return Arrays.stream(values())
                .filter(menu -> menu.getName().equals(name))
                .findFirst()
                .map(Menu::getPrice)
                .orElseThrow(() -> new IllegalArgumentException(INVALID_ORDER_ERROR_MESSAGE));
    }

    public MenuType getMenuType() {
        return menuType;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
