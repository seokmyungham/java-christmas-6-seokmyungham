package christmas.view;

import christmas.domain.event.VisitDate;
import christmas.domain.order.Order;
import christmas.domain.order.OrderMenu;

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
}
