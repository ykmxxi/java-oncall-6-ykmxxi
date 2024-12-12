package oncall.service.dto;

import java.time.DayOfWeek;
import java.time.Month;

public record WorkingMonthResponse(Month month, DayOfWeek startDayOfWeek) {
}
