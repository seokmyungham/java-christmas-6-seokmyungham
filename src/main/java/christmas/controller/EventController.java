package christmas.controller;

import christmas.domain.VisitDate;
import christmas.domain.event.EventType;
import christmas.domain.order.Order;
import christmas.service.EventService;
import christmas.view.OutputView;
import java.util.Map;

public class EventController {
    private final EventService eventService;
    private final OutputView outputView;

    public EventController() {
        this.eventService = new EventService();
        this.outputView = new OutputView();
    }

    public void processEvents(Order order, VisitDate visitDate) {
        Map<EventType, Integer> eventBenefits = eventService.manageEvents(order, visitDate);
        int totalBenefits = eventService.sumEventBenefits(eventBenefits);

        processPreview(visitDate);
        processOrderInfo(order);
        processGiftEvent();
        processBenefits(eventBenefits, totalBenefits);
        processFinalPrice(order, eventBenefits);
        processBadgeEvent(totalBenefits);
    }

    private void processPreview(VisitDate visitDate) {
        outputView.printPreviewMessage(visitDate);
    }

    private void processOrderInfo(Order order) {
        outputView.printOrderMenu(order);
        outputView.printPriceBeforeDiscount(order);
    }

    private void processGiftEvent() {
        outputView.printGiftMenus(eventService.giftEvents());
    }

    private void processFinalPrice(Order order, Map<EventType, Integer> eventBenefits) {
        int finalPrice = eventService.calculateFinalPrice(order, eventBenefits);
        outputView.printPriceAfterDiscount(finalPrice);
    }

    private void processBenefits(Map<EventType, Integer> eventBenefits, int totalBenefits) {
        outputView.printBenefitsDetails(eventBenefits, totalBenefits);
        outputView.printTotalBenefits(totalBenefits);
    }

    private void processBadgeEvent(int totalBenefits) {
        outputView.printEventBadge(eventService.giveBadge(totalBenefits));
    }
}
