package christmas.domain.event;

public enum EventType {
    CHRISTMAS_D_DAY_DISCOUNT("크리스마스 디데이 할인"),
    WEEKDAY_DISCOUNT("평일 할인"),
    WEEKEND_DISCOUNT("주말 할인"),
    STAR_DAY_DISCOUNT("특별 할인"),
    GIFT_EVENT("증정 이벤트");

    private final String name;

    EventType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
