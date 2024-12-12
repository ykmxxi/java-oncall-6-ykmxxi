package oncall.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WorkingOrderTest {

    @DisplayName("최대 근무자 인원을 초과하면 근무 순번 생성에 예외가 발생한다.")
    @Test
    void 근무_순번_생성_실패_최대_근무자_초과() {
        assertThatThrownBy(() -> new WorkingOrder(36))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("최대 근무자 인원");
    }

}
