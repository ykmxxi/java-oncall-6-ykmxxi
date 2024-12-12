package oncall.service;

import oncall.domain.WorkingMonth;
import oncall.service.dto.WorkingMonthResponse;

public class OnCallService {

    public WorkingMonthResponse createWorkingMonth(final int month, final String startDayOfWeek) {
        WorkingMonth workingMonth = new WorkingMonth(month, startDayOfWeek);
        return new WorkingMonthResponse(workingMonth.month(), workingMonth.startDayOfWeek());
    }

}
