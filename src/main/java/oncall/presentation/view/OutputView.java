package oncall.presentation.view;

import java.util.List;

import oncall.service.dto.WorkingScheduleResponse;

public class OutputView {

    public void printErrorMessage(final String message) {
        System.out.println(String.join(
                " ", "[ERROR]", message, "다시 입력해주세요."
        ));
    }

    public void printWorkingSchedule(final List<WorkingScheduleResponse> workingScheduleResponse) {
        System.out.println();
        for (WorkingScheduleResponse response : workingScheduleResponse) {
            System.out.println(formatSchedule(response));
        }
    }

    private String formatSchedule(final WorkingScheduleResponse response) {
        if (response.isPublicHoliday()) {
            return "%d월 %d일 %s(휴일) %s".formatted(
                    response.month(), response.day(), response.dayOfWeek(), response.worker());
        }
        return "%d월 %d일 %s %s".formatted(
                response.month(), response.day(), response.dayOfWeek(), response.worker());
    }

}
