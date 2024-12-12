package oncall.service.dto;

public record WorkingScheduleResponse(
        int month, int day, String dayOfWeek,
        String worker, boolean isPublicHoliday
) {
}
