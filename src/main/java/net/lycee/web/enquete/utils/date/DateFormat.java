package net.lycee.web.enquete.utils.date;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public enum DateFormat {
    YYYYMMDD("yyyyMMdd"),
    YYYYMMDD_HHMMSS("yyyyMMdd HHmmss"),

    YYYYMMDD_HYPHON("yyyy-MM-dd"),
    YYYYMMDD_HHMMSS_HYPHON("yyyy-MM-dd HH:mm:ss"),

    YYYYMMDD_POSIX("yyyyMMdd'T'hhmmss")
    ;

    private final DateTimeFormatter format;

    DateFormat(String format) {
        this.format = DateTimeFormatter.ofPattern(format);
    }

    public LocalDateTime parse(String date) {
        return LocalDateTime.parse(date, this.format);
    }

    public static LocalDateTime parseToDateTime(String date) {

        DateTimeParseException last = null;
        for (DateFormat format : DateFormat.values() ) {
            try {
                return format.parse(date);
            } catch (DateTimeParseException e) {
                last = e;
            }
        }
        assert last != null;
        throw last;
    }
}
