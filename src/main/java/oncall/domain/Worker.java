package oncall.domain;

public class Worker {

    private static final int MAX_NAME_LENGTH = 5;

    private final String name;
    private final WorkingOrder workingOrder;

    public Worker(final String name, final int weekdayOrder, final int holidayOrder) {
        validateName(name);
        this.name = name;
        this.workingOrder = new WorkingOrder(weekdayOrder, holidayOrder);
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

}
