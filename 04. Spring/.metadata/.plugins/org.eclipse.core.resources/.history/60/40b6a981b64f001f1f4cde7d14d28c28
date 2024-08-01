package utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {

    // Define your desired date and time formats
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    public static String formatLocalDate(LocalDate date) {
        return date != null ? date.format(DATE_FORMATTER) : "-";
    }

    public static String formatLocalTime(LocalTime time) {
        return time != null ? time.format(TIME_FORMATTER) : "-";
    }

    public static LocalDate parseLocalDate(String dateString) {
        return dateString != null && !dateString.isEmpty() ? LocalDate.parse(dateString, DATE_FORMATTER) : null;
    }

    public static LocalTime parseLocalTime(String timeString) {
        return timeString != null && !timeString.isEmpty() ? LocalTime.parse(timeString, TIME_FORMATTER) : null;
    }
}

