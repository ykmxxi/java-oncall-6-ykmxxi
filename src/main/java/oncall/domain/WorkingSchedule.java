package oncall.domain;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;

public class WorkingSchedule {

    private static final int MIN_WORKER_COUNT = 5;

    private final Deque<Working> weekdaySchedule;
    private final Deque<Working> holidaySchedule;

    public WorkingSchedule(final Deque<Working> weekdaySchedule, final Deque<Working> holidaySchedule) {
        validate(weekdaySchedule, holidaySchedule);
        this.weekdaySchedule = weekdaySchedule;
        this.holidaySchedule = holidaySchedule;
    }

    public List<Working> createMonthSchedule(final WorkingMonth workingMonth) {
        int startDay = 1;
        int endDay = workingMonth.calculateEndDay();
        List<Working> monthSchedule = initMonthSchedule(workingMonth, startDay);
        for (int workDay = startDay + 1; workDay <= endDay; workDay++) {
            if (workingMonth.isHoliday(workDay)) {
                addSchedule(monthSchedule, holidaySchedule);
                continue;
            }
            addSchedule(monthSchedule, weekdaySchedule);
        }
        return monthSchedule;
    }

    private List<Working> initMonthSchedule(final WorkingMonth workingMonth, final int startDay) {
        List<Working> monthSchedule = new ArrayList<>();
        if (workingMonth.isHoliday(startDay)) {
            addSchedule(monthSchedule, holidaySchedule);
            return monthSchedule;
        }
        addSchedule(monthSchedule, weekdaySchedule);
        return monthSchedule;
    }

    private void addSchedule(final List<Working> monthSchedule, final Deque<Working> schedule) {
        if (monthSchedule.isEmpty() || !isContinuousWorking(monthSchedule, schedule)) {
            Working working = schedule.pollFirst();
            monthSchedule.add(working);
            working.increaseWorkCount();
            schedule.offerLast(working);
            return;
        }
        addContinuousSchedule(monthSchedule, schedule);
    }

    private boolean isContinuousWorking(final List<Working> monthSchedule, final Deque<Working> schedule) {
        return monthSchedule.getLast().equals(schedule.peek());
    }

    private void addContinuousSchedule(final List<Working> monthSchedule, final Deque<Working> schedule) {
        Working waitingWorking = schedule.pollFirst();
        Working actualWorking = schedule.pollFirst();
        monthSchedule.add(actualWorking);
        schedule.offerFirst(waitingWorking);
        schedule.offerLast(actualWorking);
    }

    private void validate(final Deque<Working> weekdaySchedule, final Deque<Working> holidaySchedule) {
        validateDuplication(weekdaySchedule);
        validateDuplication(holidaySchedule);
        validateMinimumWorker(weekdaySchedule);
        validateMinimumWorker(holidaySchedule);
        validateSameWorkers(weekdaySchedule, holidaySchedule);
    }

    private void validateDuplication(final Deque<Working> schedule) {
        if (new HashSet<>(schedule).size() != schedule.size()) {
            throw new IllegalArgumentException("같은 근무자가 두 번 이상 근무할 수 없습니다.");
        }
    }

    private void validateMinimumWorker(final Deque<Working> schedule) {
        if (schedule.size() < MIN_WORKER_COUNT) {
            throw new IllegalArgumentException("최소 %d명의 근무자가 필요합니다.".formatted(MIN_WORKER_COUNT));
        }
    }

    private void validateSameWorkers(final Deque<Working> weekdaySchedule, final Deque<Working> holidaySchedule) {
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
