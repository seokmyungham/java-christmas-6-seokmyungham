package christmas.view;

import christmas.domain.Menu;
import christmas.domain.event.VisitDate;
import christmas.domain.order.Order;
import christmas.domain.order.OrderMenu;
import java.util.Map;

public class OutputView {
    private static final String PREVIEW_MESSAGE = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!%n%n";
    private static final String ORDER_MENU_MESSAGE = "<주문 메뉴>";
    private static final String MENU_COUNT_MESSAGE = "%s %d개%n";

    public void printPreviewMessage(VisitDate visitDate) {
        System.out.printf(PREVIEW_MESSAGE, visitDate.getVisitDate());
    }

    public void printOrderMenu(Order order) {
        System.out.println(ORDER_MENU_MESSAGE);
        for (OrderMenu orderMenu : order.getOrders()) {
            System.out.printf(MENU_COUNT_MESSAGE, orderMenu.getMenu(), orderMenu.getCount());
        }
    }

    public void printPriceBeforeDiscount(Order order) {
        System.out.printf("%n<할인 전 총주문 금액>%n%,d원%n", order.totalPrice());
    }

    public void printGiftMenus(Map<Menu, Integer> gifts) {
        System.out.printf("%n<증정 메뉴>%n");
        if (gifts.isEmpty()) {
            System.out.printf("없음%n");
        }
        for (Menu gift : gifts.keySet()) {
            System.out.printf("%s %d개%n", gift.getName(), gifts.get(gift));
        }
    }
}
