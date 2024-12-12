package oncall.presentation.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public String readWorkingMonth() {
        System.out.print("비상 근무를 배정할 월과 시작 요일을 입력하세요> ");
        String input = readString();
        InputValidator.validate(input);
        return input;
    }

    private int readInteger() {
        try {
            return Integer.parseInt(readString());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("");
        }
    }

    private String readString() {
        return Console.readLine()
                .strip();
    }

}
