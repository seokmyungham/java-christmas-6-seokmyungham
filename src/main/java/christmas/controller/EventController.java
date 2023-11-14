package christmas.controller;

import christmas.domain.event.VisitDate;
import christmas.domain.order.Count;
import christmas.domain.order.MenuName;
import christmas.domain.order.Order;
import christmas.domain.order.OrderMenu;
import christmas.service.EventService;
import christmas.view.InputView;
import java.util.ArrayList;
import java.util.List;

public class EventController {
    private final InputView inputView;
    private final InputValidator inputValidator;
    private final EventService eventService;

    public EventController() {
        this.inputView = new InputView();
        this.inputValidator = new InputValidator();
        this.eventService = new EventService();
    }

    public void orderStart() {
        getVisitDate();
        getOrder();
    }

    private VisitDate getVisitDate() {
        try {
            String visitDateInput = inputView.inputVisitDate();
            inputValidator.validateIsNumeric(visitDateInput);
            return new VisitDate(Integer.parseInt(visitDateInput));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getVisitDate();
        }
    }

    private Order getOrder() {
        try {
            String orderInput = inputView.inputOrderMenu();
            inputValidator.validateInputFormat(orderInput);
            return new Order(getOrderMenus(orderInput));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getOrder();
        }
    }

    private List<OrderMenu> getOrderMenus(String orderInput) {
        List<OrderMenu> orders = new ArrayList<>();

        for (String parseByComma : InputParser.parseInputByComma(orderInput)) {
            OrderMenu orderMenu = getOrderMenu(parseByComma);
            orders.add(orderMenu);
        }
        return orders;
    }

    private OrderMenu getOrderMenu(String parseByComma) {
        String menuName = InputParser.parseInputByHYPHEN(parseByComma).get(0);
        String count = InputParser.parseInputByHYPHEN(parseByComma).get(1);

        return new OrderMenu(new MenuName(menuName), new Count(Integer.parseInt(count)));
    }
}
