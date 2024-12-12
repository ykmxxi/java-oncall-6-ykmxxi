package oncall.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class WorkerTest {

    @DisplayName("근무자 이름이 길거나 이름에 공백이 존재하면 예외가 발생한다.")
    @ValueSource(strings = {"", " ", "루루루루루루", "루 루"})
    @ParameterizedTest
    void 근무자_생성_실패(String name) {
        assertThatThrownBy(() -> new Worker(name))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
