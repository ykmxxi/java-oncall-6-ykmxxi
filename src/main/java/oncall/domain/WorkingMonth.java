package oncall.domain;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.Month;

public class WorkingMonth {

    private final Month month;
    private final DayOfWeek startDayOfWeek;

    public WorkingMonth(final int month, final String startDayOfWeek) {
        this.month = validateMonth(month);
        this.startDayOfWeek = KoreaDayOfWeek.getDayOfWeekFromKorean(startDayOfWeek);
    }

    private Month validateMonth(final int month) {
        try {
            return Month.of(month);
        } catch (DateTimeException e) {
            throw new IllegalArgumentException("유효하지 않은 월 입력 값입니다.");
        }
    }

}
