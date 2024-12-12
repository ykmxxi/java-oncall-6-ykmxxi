package oncall.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WorkingScheduleTest {

    private List<Working> workings;

    @BeforeEach
    void setUp() {
        workings = new ArrayList<>();
        workings.add(new Working(new Worker("a"), new WorkingOrder(1)));
        workings.add(new Working(new Worker("b"), new WorkingOrder(2)));
        workings.add(new Working(new Worker("c"), new WorkingOrder(3)));
        workings.add(new Working(new Worker("d"), new WorkingOrder(4)));
        workings.add(new Working(new Worker("e"), new WorkingOrder(5)));
    }

    @DisplayName("같은 근무자가 두 번 이상 근무하면 예외가 발생한다.")
    @Test
    void 근무_스케줄_생성_실패_중복() {
        Queue<Working> weekdaySchedule = new PriorityQueue<>(new ArrayList<>(workings));
        workings.add(new Working(new Worker("a"), new WorkingOrder(6)));
        Queue<Working> weekendSchedule = new PriorityQueue<>(workings);

        assertThatThrownBy(() -> new WorkingSchedule(weekdaySchedule, weekendSchedule))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("최소 근무자 인원을 넘지 못하면 예외가 발생한다.")
    @Test
    void 근무_스케줄_생성_실패_최소_인원() {
        Queue<Working> weekdaySchedule = new PriorityQueue<>(new ArrayList<>(workings));
        workings.removeLast();
        Queue<Working> weekendSchedule = new PriorityQueue<>(workings);

        assertThatThrownBy(() -> new WorkingSchedule(weekdaySchedule, weekendSchedule))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("평일/휴일 근무자가 같지 않으면 예외가 발생한다.")
    @Test
    void 근무_스케줄_생성_실패_근무_인원_다름() {
        Queue<Working> weekdaySchedule = new PriorityQueue<>(new ArrayList<>(workings));
        workings.removeLast();
        workings.addLast(new Working(new Worker("f"), new WorkingOrder(5)));
        Queue<Working> weekendSchedule = new PriorityQueue<>(workings);

        assertThatThrownBy(() -> new WorkingSchedule(weekdaySchedule, weekendSchedule))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
