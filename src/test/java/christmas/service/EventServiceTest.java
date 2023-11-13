package christmas.service;

import static christmas.domain.event.EventType.*;
import static christmas.domain.event.EventType.CHRISTMAS_D_DAY_DISCOUNT;
import static christmas.domain.event.EventType.WEEKEND_DISCOUNT;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.event.EventType;
import christmas.domain.event.VisitDate;
import christmas.domain.order.Count;
import christmas.domain.order.MenuName;
import christmas.domain.order.Order;
import christmas.domain.order.OrderMenu;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventServiceTest {
    private Order orders;

    @BeforeEach
    void setUp() {
        orders = new Order(List.of(
                new OrderMenu(new MenuName("타파스"), new Count(2)),
                new OrderMenu(new MenuName("티본스테이크"), new Count(1)),
                new OrderMenu(new MenuName("해산물파스타"), new Count(1)),
                new OrderMenu(new MenuName("아이스크림"), new Count(2))
        ));
    }

    @Test
    void manageDiscount() {
        Map<EventType, Integer> expectedBenefits = new HashMap<>();
        expectedBenefits.put(WEEKEND_DISCOUNT, 4046);
        expectedBenefits.put(CHRISTMAS_D_DAY_DISCOUNT, 3200);
        expectedBenefits.put(WEEKDAY_DISCOUNT, 0);
        expectedBenefits.put(STAR_DAY_DISCOUNT, 0);
        expectedBenefits.put(GIFT_EVENT, 0);

        EventService eventService = new EventService();
        Map<EventType, Integer> eventBenefits = eventService.manageEvents(orders, new VisitDate(23));

        assertThat(eventBenefits).isEqualTo(expectedBenefits);
    }

    @DisplayName("총 주문 가격이 이벤트 적용 조건(10000원 이상)을 만족하고 참을 반환한다.")
    @Test
    void meetRequirementsTrueTest() {
        Order orders = new Order(List.of(new OrderMenu(new MenuName("티본스테이크"), new Count(1))));

        EventService eventService = new EventService();
        assertThat(eventService.meetRequirements(orders)).isTrue();
    }

    @DisplayName("총 주문 가격이 이벤트 적용 조건(10000원 이상)을 만족하지 못하고 거짓을 반환한다.")
    @Test
    void meetRequirementsFalseTest() {
        Order orders = new Order(List.of(new OrderMenu(new MenuName("양송이수프"), new Count(1))));

        EventService eventService = new EventService();
        assertThat(eventService.meetRequirements(orders)).isFalse();
    }
}