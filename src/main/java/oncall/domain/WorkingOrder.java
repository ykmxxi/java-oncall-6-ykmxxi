package oncall.domain;

public class WorkingOrder {

    private static final int MAX_WORKER_COUNT = 35;

    private final int weekdayOrder;
    private final int holidayOrder;

    public WorkingOrder(final int weekdayOrder, final int holidayOrder) {
        validate(weekdayOrder, holidayOrder);
        this.weekdayOrder = weekdayOrder;
        this.holidayOrder = holidayOrder;
    }

    private void validate(final int weekdayOrder, final int holidayOrder) {
        if (weekdayOrder > MAX_WORKER_COUNT || holidayOrder > MAX_WORKER_COUNT) {
            throw new IllegalArgumentException("최대 근무자 인원 %d명을 초과했습니다.".formatted(MAX_WORKER_COUNT));
        }
    }

}
