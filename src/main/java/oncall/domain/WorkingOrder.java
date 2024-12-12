package oncall.domain;

public class WorkingOrder implements Comparable<WorkingOrder> {

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
    public int compareTo(final WorkingOrder o) {
        return this.order - o.order;
    }

    @Override
    public String toString() {
        return "WorkingOrder{" +
                "order=" + order +
                '}';
    }

}
