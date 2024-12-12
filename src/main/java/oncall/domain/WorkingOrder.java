package oncall.domain;

import java.util.Objects;

public class WorkingOrder {

    private static final int MAX_WORKER_COUNT = 35;

    private final int order;

    public WorkingOrder(final int order) {
        validate(order);
        this.order = order;
    }

    private void validate(final int order) {
        if (order > MAX_WORKER_COUNT) {
            throw new IllegalArgumentException("최대 근무자 인원 %d명을 초과했습니다.".formatted(MAX_WORKER_COUNT));
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WorkingOrder that)) {
            return false;
        }
        return order == that.order;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(order);
    }

    @Override
    public String toString() {
        return "WorkingOrder{" +
                "order=" + order +
                '}';
    }

}
