package oncall.presentation.view;

public class OutputView {

    public void printErrorMessage(final String message) {
        System.out.println(String.join(
                " ", "[ERROR]", message, "다시 입력해주세요."
        ));
    }

}
