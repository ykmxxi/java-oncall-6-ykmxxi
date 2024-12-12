package oncall.presentation;

import java.util.Arrays;
import java.util.List;

import oncall.presentation.view.InputView;
import oncall.presentation.view.OutputView;
import oncall.service.OnCallService;
import oncall.service.dto.WorkingMonthResponse;

public class OnCallClient {

    private final InputView inputView;
    private final OutputView outputView;
    private final OnCallService onCallService;

    public OnCallClient(final InputView inputView, final OutputView outputView, final OnCallService onCallService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.onCallService = onCallService;
    }

    public void run() {
        WorkingMonthResponse workingMonth = createWorkingMonth();
    }

    private WorkingMonthResponse createWorkingMonth() {
        return RetryHandler.retry(() -> {
            List<String> workingMonthInput = splitInput(inputView.readWorkingMonth());
            return onCallService.createWorkingMonth(
                    Integer.parseInt(workingMonthInput.getFirst()), workingMonthInput.getLast());
        }, outputView);
    }

    private List<String> splitInput(final String input) {
        return Arrays.stream(input.split(","))
                .map(String::strip)
                .toList();
    }

}
