package oncall.domain;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

public class WorkingMonth {

    private static final boolean NO_LEAP_YEAR = false;

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

    public boolean isHoliday(final int targetDay) {
        DayOfWeek targetDayOfWeek = startDayOfWeek.plus(targetDay - 1);
        return isWeekend(targetDayOfWeek) || PublicHoliday.isPublicHoliday(month.getValue(), targetDay);
    }

    private boolean isWeekend(final DayOfWeek targetDayOfWeek) {
        return targetDayOfWeek.equals(DayOfWeek.SATURDAY) || targetDayOfWeek.equals(DayOfWeek.SUNDAY);
    }

    public int calculateEndDay() {
        return month.length(NO_LEAP_YEAR);
    }

    public String getKoreanDayOfWeek(final int day) {
        DayOfWeek dayOfWeek = startDayOfWeek.plus(day - 1);
        return dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.KOREA);
    }

    public Month month() {
        return month;
    }

    public DayOfWeek startDayOfWeek() {
        return startDayOfWeek;
    }

}
