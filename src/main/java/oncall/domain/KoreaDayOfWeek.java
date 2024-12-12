package oncall.domain;

import static java.time.format.TextStyle.SHORT;
import static java.util.Locale.KOREA;

import java.time.DayOfWeek;
import java.util.Arrays;

public enum KoreaDayOfWeek {

    KOREA_MONDAY(DayOfWeek.MONDAY, getKoreanDisplayName(DayOfWeek.MONDAY)),
    KOREA_TUESDAY(DayOfWeek.TUESDAY, getKoreanDisplayName(DayOfWeek.TUESDAY)),
    KOREA_WEDNESDAY(DayOfWeek.WEDNESDAY, getKoreanDisplayName(DayOfWeek.WEDNESDAY)),
    KOREA_THURSDAY(DayOfWeek.THURSDAY, getKoreanDisplayName(DayOfWeek.THURSDAY)),
    KOREA_FRIDAY(DayOfWeek.FRIDAY, getKoreanDisplayName(DayOfWeek.FRIDAY)),
    KOREA_SATURDAY(DayOfWeek.SATURDAY, getKoreanDisplayName(DayOfWeek.SATURDAY)),
    KOREA_SUNDAY(DayOfWeek.SUNDAY, getKoreanDisplayName(DayOfWeek.SUNDAY));

    private final DayOfWeek value;
    private final String koreanDisplayName;

    private static String getKoreanDisplayName(final DayOfWeek dayOfWeek) {
        return dayOfWeek.getDisplayName(SHORT, KOREA);
    }

    KoreaDayOfWeek(final DayOfWeek value, final String koreanDisplayName) {
        this.value = value;
        this.koreanDisplayName = koreanDisplayName;
    }

    public static DayOfWeek getDayOfWeekFromKorean(final String korean) {
        return Arrays.stream(KoreaDayOfWeek.values())
                .filter(koreaDayOfWeek -> koreaDayOfWeek.koreanDisplayName.equals(korean))
                .findAny()
                .map(koreaDayOfWeek -> koreaDayOfWeek.value)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 요일 입력 값입니다."));
    }

}
