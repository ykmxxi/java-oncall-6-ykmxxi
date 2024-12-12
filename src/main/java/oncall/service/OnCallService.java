package oncall.service;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import oncall.domain.PublicHoliday;
import oncall.domain.Worker;
import oncall.domain.Working;
import oncall.domain.WorkingMonth;
import oncall.domain.WorkingOrder;
import oncall.domain.WorkingSchedule;
import oncall.service.dto.WorkingMonthResponse;
import oncall.service.dto.WorkingScheduleResponse;

public class OnCallService {

    public WorkingMonthResponse createWorkingMonth(final int month, final String startDayOfWeek) {
        WorkingMonth workingMonth = new WorkingMonth(month, startDayOfWeek);
        return new WorkingMonthResponse(month, startDayOfWeek);
    }

    public List<WorkingScheduleResponse> createWorkingSchedule(
            WorkingMonthResponse workingMonthResponse,
            final List<String> weekdayWorkingOrder,
            final List<String> holidayWorkingOrder
    ) {
        Deque<Working> weekdaySchedule = createWorkingSchedule(weekdayWorkingOrder);
        Deque<Working> holidaySchedule = createWorkingSchedule(holidayWorkingOrder);
        WorkingSchedule workingSchedule = new WorkingSchedule(weekdaySchedule, holidaySchedule);
        WorkingMonth workingMonth = new WorkingMonth(
                workingMonthResponse.month(), workingMonthResponse.startDayOfWeek());
        List<Working> monthSchedule = workingSchedule.createMonthSchedule(workingMonth);
        return toWorkingScheduleResponse(workingMonth, monthSchedule);
    }

    private Deque<Working> createWorkingSchedule(final List<String> workingOrders) {
        Deque<Working> workingSchedule = new ArrayDeque<>();
        for (int order = 0; order < workingOrders.size(); order++) {
            Worker worker = new Worker(workingOrders.get(order));
            WorkingOrder workingOrder = new WorkingOrder(order + 1);
            workingSchedule.add(new Working(worker, workingOrder));
        }
        return workingSchedule;
    }

    private List<WorkingScheduleResponse> toWorkingScheduleResponse(
            final WorkingMonth workingMonth, final List<Working> monthSchedule
    ) {
        List<WorkingScheduleResponse> responses = new ArrayList<>();
        for (int day = 1; day <= workingMonth.calculateEndDay(); day++) {
            int month = workingMonth.month().getValue();
            String dayOfWeek = workingMonth.getKoreanDayOfWeek(day);
            String worker = monthSchedule.get(day - 1).worker();
            boolean isPublicHoliday = PublicHoliday.isPublicHoliday(month, day);
            responses.add(new WorkingScheduleResponse(month, day, dayOfWeek, worker, isPublicHoliday));
        }
        return responses;
    }

}
