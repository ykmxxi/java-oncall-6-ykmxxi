package oncall.domain;

import java.util.Objects;

public class Worker implements Comparable<Worker> {

    private static final int MAX_NAME_LENGTH = 5;

    private final String name;
    private int workingCount;

    public Worker(final String name) {
        validateName(name);
        this.name = name;
        this.workingCount = 0;
    }

    private void validateName(final String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("이름 값이 존재하지 않습니다.");
        }
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("%s은(는) 너무 긴 근무자 이름 입니다.".formatted(name));
        }
        if (name.contains(" ")) {
            throw new IllegalArgumentException("%s은(는) 공백을 포함한 이름 입니다.".formatted(name));
        }
    }

    @Override
    public int compareTo(final Worker o) {
        return this.workingCount - o.workingCount;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Worker worker)) {
            return false;
        }
        return Objects.equals(name, worker.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        return "Worker{" +
                "name='" + name + '\'' +
                ", workingCount=" + workingCount +
                '}';
    }

}
