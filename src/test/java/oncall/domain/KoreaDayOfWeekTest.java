package oncall.domain;

import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.DayOfWeek;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class KoreaDayOfWeekTest {

    @DisplayName("월 ~ 일 맞는 요일을 반환한다.")
    @MethodSource("provideKoreanAndExpected")
    @ParameterizedTest
    void 한국어_요일에_맞는_요일_반환(String korean, DayOfWeek expected) {
        assertThat(KoreaDayOfWeek.getDayOfWeekFromKorean(korean)).isEqualTo(expected);
    }

    @DisplayName("월 ~ 일 이외에 값이 들어오면 예외가 발생한다.")
    @Test
    void 한국어_요일에_맞는_요일_반환_실패() {
        assertThatThrownBy(() -> KoreaDayOfWeek.getDayOfWeekFromKorean("워"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효하지 않은 요일 입력 값입니다.");
    }

    private static Stream<Arguments> provideKoreanAndExpected() {
        return Stream.of(
                Arguments.of("월", MONDAY),
                Arguments.of("화", TUESDAY),
                Arguments.of("수", WEDNESDAY),
                Arguments.of("목", THURSDAY),
                Arguments.of("금", FRIDAY),
                Arguments.of("토", SATURDAY),
                Arguments.of("일", SUNDAY)
        );
    }

}
