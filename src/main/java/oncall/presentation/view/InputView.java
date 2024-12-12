package oncall.presentation.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public String readWorkingMonth() {
        System.out.print("비상 근무를 배정할 월과 시작 요일을 입력하세요> ");
        return readString();
    }

    public String readWeekdayWorkingOrder() {
        System.out.print("평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");
        return readString();
    }

    public String readHolidayWorkingOrder() {
        System.out.print("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");
        return readString();
    }

    private String readString() {
        String input = Console.readLine()
                .strip();
        InputValidator.validate(input);
        return input;
    }

}
