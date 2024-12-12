package oncall.domain;

import java.util.HashSet;
import java.util.Queue;

public class WorkingSchedule {

    private static final int MIN_WORKER_COUNT = 5;
    private final Queue<Working> weekdaySchedule;
    private final Queue<Working> holidaySchedule;

    public WorkingSchedule(final Queue<Working> weekdaySchedule, final Queue<Working> holidaySchedule) {
        validate(weekdaySchedule, holidaySchedule);
        this.weekdaySchedule = weekdaySchedule;
        this.holidaySchedule = holidaySchedule;
    }

    private void validate(final Queue<Working> weekdaySchedule, final Queue<Working> holidaySchedule) {
        validateDuplication(weekdaySchedule);
        validateDuplication(holidaySchedule);
        validateMinimumWorker(weekdaySchedule);
        validateMinimumWorker(holidaySchedule);
        validateSameWorkers(weekdaySchedule, holidaySchedule);
    }

    private void validateDuplication(final Queue<Working> schedule) {
        if (new HashSet<>(schedule).size() != schedule.size()) {
            throw new IllegalArgumentException("같은 근무자가 두 번 이상 근무할 수 없습니다.");
        }
    }

    private void validateMinimumWorker(final Queue<Working> schedule) {
        if (schedule.size() < MIN_WORKER_COUNT) {
            throw new IllegalArgumentException("최소 %d명의 근무자가 필요합니다.".formatted(MIN_WORKER_COUNT));
        }
    }

    private void validateSameWorkers(final Queue<Working> weekdaySchedule, final Queue<Working> holidaySchedule) {
        if (!weekdaySchedule.containsAll(holidaySchedule)) {
            throw new IllegalArgumentException("평일/휴일 근무자가 다릅니다.");
        }
    }

    @Override
    public String toString() {
        return "WorkingSchedule{" +
                "weekdaySchedule=" + weekdaySchedule +
                ", holidaySchedule=" + holidaySchedule +
                '}';
    }

}
