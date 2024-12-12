package oncall.presentation;

import java.util.Arrays;
import java.util.List;

import oncall.presentation.view.InputView;
import oncall.presentation.view.OutputView;
import oncall.service.OnCallService;
import oncall.service.dto.WorkingMonthResponse;
import oncall.service.dto.WorkingScheduleResponse;

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
        List<WorkingScheduleResponse> workingScheduleResponse = createWorkingSchedule(workingMonth);
        outputView.printWorkingSchedule(workingScheduleResponse);
    }

    private WorkingMonthResponse createWorkingMonth() {
        return RetryHandler.retry(() -> {
            List<String> workingMonthInput = splitInput(inputView.readWorkingMonth());
            return onCallService.createWorkingMonth(
                    parseInt(workingMonthInput), workingMonthInput.getLast());
        }, outputView);
    }

    private int parseInt(final List<String> workingMonthInput) {
        try {
            return Integer.parseInt(workingMonthInput.getFirst());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("숫자를 입력해 주세요.");
        }
    }

    private List<WorkingScheduleResponse> createWorkingSchedule(final WorkingMonthResponse workingMonthResponse) {
        return RetryHandler.retry(() -> {
            List<String> weekdayWorkingOrder = splitInput(inputView.readWeekdayWorkingOrder());
            List<String> holidayWorkingOrder = splitInput(inputView.readHolidayWorkingOrder());
            return onCallService.createWorkingSchedule(workingMonthResponse, weekdayWorkingOrder, holidayWorkingOrder);
        }, outputView);
    }

    private List<String> splitInput(final String input) {
        return Arrays.stream(input.split(","))
                .map(String::strip)
                .toList();
    }

}
