package christmas.controller;

import christmas.domain.event.VisitDate;
import christmas.service.EventService;
import christmas.view.InputView;

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

}
