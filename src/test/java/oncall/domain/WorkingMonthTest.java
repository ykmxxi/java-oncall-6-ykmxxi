package oncall.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class WorkingMonthTest {

    @DisplayName("1 ~ 12 월이 아닌 다른 숫자를 입력하면 근무 배정 월 생성 시 예외가 발생한다")
    @ValueSource(ints = {0, 13})
    @ParameterizedTest
    void 근무_배정_월_생성_실패_잘못된_월_숫자(int month) {
        assertThatThrownBy(() -> new WorkingMonth(month, "월"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효하지 않은 월 입력 값입니다.");
    }

    @DisplayName("월 ~ 일 이외에 다른 값을 입력하면 근무 배정 월 생성 시 예외가 발생한다.")
    @Test
    void 근무_배정_월_생성_실패_잘못된_요일() {
        assertThatThrownBy(() -> new WorkingMonth(1, "워"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효하지 않은 요일 입력 값입니다.");
    }

    @DisplayName("1 ~ 12월, 월 ~ 일요일을 입력하면 근무 배정 월 생성이 성공한다.")
    @Test
    void 근무_배정_월_생성_성공() {
        assertDoesNotThrow(() -> new WorkingMonth(1, "월"));
    }

    @DisplayName("해당 월의 마지막 날자를 알려준다.")
    @CsvSource(value = {"1,31", "2,28", "3,31", "4,30", "5,31", "6,30",
            "7,31", "8,31", "9,30", "10,31", "11,30", "12,31"})
    @ParameterizedTest
    void 해당_월의_마지막_날자를_알려준다(int month, int endDay) {
        WorkingMonth workingMonth = new WorkingMonth(month, "월");

        assertThat(workingMonth.calculateEndDay()).isEqualTo(endDay);
    }

}
