package christmas.view;

import christmas.domain.Menu;
import christmas.domain.VisitDate;
import christmas.domain.event.Badge;
import christmas.domain.event.EventType;
import christmas.domain.order.Order;
import christmas.domain.order.OrderMenu;
import java.util.Map;

public class OutputView {
    private static final String PREVIEW_MESSAGE = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!%n%n";
    private static final String ORDER_MENU_MESSAGE = "<주문 메뉴>";
    private static final String ORDER_MENU_DETAILS_MESSAGE = "%s %d개%n";
    private static final String PRICE_BEFORE_DISCOUNT_MESSAGE = "%n<할인 전 총주문 금액>%n%,d원%n";
    private static final String GIFT_MENU_MESSAGE = "%n<증정 메뉴>%n";
    private static final String GIFT_MENU_DETAILS_MESSAGE = "%s %d개%n";
    private static final String NONE_MESSAGE = "없음%n";
    private static final String BENEFITS_BREAKDOWN_MESSAGE = "%n<혜택 내역>%n";
    private static final String BENEFITS_DETAILS_MESSAGE = "%s: -%,d원%n";
    private static final String TOTAL_BENEFITS_PRICE_MESSAGE = "%n<총혜택 금액>%n%,d원%n";
    private static final String PRICE_AFTER_DISCOUNT_MESSAGE = "%n<할인 후 예상 결제 금액>%n%,d원%n";
    private static final String EVENT_BADGE_MESSAGE = "%n<12월 이벤트 배지>%n%s";


    public void printPreviewMessage(VisitDate visitDate) {
        System.out.printf(PREVIEW_MESSAGE, visitDate.getVisitDate());
    }

    public void printOrderMenu(Order order) {
        System.out.println(ORDER_MENU_MESSAGE);
        for (OrderMenu orderMenu : order.getOrders()) {
            System.out.printf(ORDER_MENU_DETAILS_MESSAGE, orderMenu.getMenu(), orderMenu.getCount());
        }
    }

    public void printPriceBeforeDiscount(Order order) {
        System.out.printf(PRICE_BEFORE_DISCOUNT_MESSAGE, order.totalPrice());
    }

    public void printGiftMenus(Map<Menu, Integer> gifts) {
        System.out.printf(GIFT_MENU_MESSAGE);
        printNoGift(gifts);
        for (Menu gift : gifts.keySet()) {
            System.out.printf(GIFT_MENU_DETAILS_MESSAGE, gift.getName(), gifts.get(gift));
        }
    }

    private void printNoGift(Map<Menu, Integer> gifts) {
        if (gifts.isEmpty()) {
            System.out.printf(NONE_MESSAGE);
        }
    }

    public void printBenefitsDetails(Map<EventType, Integer> eventBenefits, int totalBenefits) {
        System.out.printf(BENEFITS_BREAKDOWN_MESSAGE);
        printNoBenefits(totalBenefits);
        for (EventType eventType : eventBenefits.keySet()) {
            if (eventBenefits.get(eventType) != 0) {
                System.out.printf(BENEFITS_DETAILS_MESSAGE, eventType.getName(), eventBenefits.get(eventType));
            }
        }
    }

    private void printNoBenefits(int totalBenefits) {
        if (totalBenefits == 0) {
            System.out.printf(NONE_MESSAGE);
        }
    }

    public void printTotalBenefits(int totalBenefits) {
        System.out.printf(TOTAL_BENEFITS_PRICE_MESSAGE, -totalBenefits);
    }

    public void printPriceAfterDiscount(int finalPrice) {
        System.out.printf(PRICE_AFTER_DISCOUNT_MESSAGE, finalPrice);
    }

    public void printEventBadge(Badge badge) {
        System.out.printf(EVENT_BADGE_MESSAGE, badge.getName());
    }
}