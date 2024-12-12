package oncall.domain;

import java.util.Objects;

public class Working {

    private final Worker worker;
    private final WorkingOrder workingOrder;

    public Working(final Worker worker, final WorkingOrder workingOrder) {
        this.worker = worker;
        this.workingOrder = workingOrder;
    }

    public void increaseWorkCount() {
        worker.work();
    }

    public String worker() {
        return worker.name();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Working working)) {
            return false;
        }
        return Objects.equals(worker, working.worker);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(worker);
    }

    @Override
    public String toString() {
        return "Working{" +
                "worker=" + worker +
                ", workingOrder=" + workingOrder +
                '}';
    }

}
