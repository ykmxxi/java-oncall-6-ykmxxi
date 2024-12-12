package oncall.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PublicHolidayTest {

    @DisplayName("월과 일을 숫자로 입력하면 해당 날자가 공휴일인지 확인한다.")
    @CsvSource(value = {"1,1,true", "1,2,false"})
    @ParameterizedTest
    void 공휴일_인지_확인(int month, int day, boolean expected) {
        assertThat(PublicHoliday.isPublicHoliday(month, day)).isEqualTo(expected);
    }

}
