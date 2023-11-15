package christmas.controller;

import christmas.domain.VisitDate;
import christmas.domain.order.Count;
import christmas.domain.order.MenuName;
import christmas.domain.order.Order;
import christmas.domain.order.OrderMenu;
import christmas.view.InputView;
import java.util.ArrayList;
import java.util.List;

public class OrderController {
    private static final String DELIMITER_COMMA = ",";
    private static final String DELIMITER_HYPHEN = "-";

    private final InputView inputView;
    private final InputValidator inputValidator;

    public OrderController() {
        this.inputView = new InputView();
        this.inputValidator = new InputValidator();
    }

    public Order processGetOrder() {
        return getOrder();
    }

    public VisitDate processGetVisitDate() {
        return getVisitDate();
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

        for (String menuHyphenCount : InputParser.parseInputByDelimiter(orderInput, DELIMITER_COMMA)) {
            OrderMenu orderMenu = getOrderMenu(menuHyphenCount);
            orders.add(orderMenu);
        }
        return orders;
    }

    private OrderMenu getOrderMenu(String menuHyphenCount) {
        String menuName = InputParser.parseInputByDelimiter(menuHyphenCount, DELIMITER_HYPHEN).get(0);
        String count = InputParser.parseInputByDelimiter(menuHyphenCount, DELIMITER_HYPHEN).get(1);

        return new OrderMenu(new MenuName(menuName), new Count(Integer.parseInt(count)));
    }
}
