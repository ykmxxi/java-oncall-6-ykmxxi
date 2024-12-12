package oncall.domain;

import java.util.Arrays;

public enum PublicHoliday {

    NEW_YEARS_DAY(1, 1),
    INDEPENDENT_MOVEMENT_DAY(3, 1),
    CHILDREN_DAY(5, 5),
    MEMORIAL_DAY(6, 6),
    LIBERATION_DAY(8, 15),
    NATIONAL_FOUNDATION_DAY(10, 3),
    KOREAN_DAY(10, 9),
    CHRISTMAS(12, 25);

    private final int month;
    private final int day;

    PublicHoliday(final int month, final int day) {
        this.month = month;
        this.day = day;
    }

    public static boolean isPublicHoliday(final int month, final int day) {
        return Arrays.stream(values())
                .anyMatch(holiday -> holiday.month == month && holiday.day == day);
    }

}
