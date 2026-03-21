package com.ibs.util;

import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class DateUtils {

    private static final DateTimeFormatter HEADER_FORMATTER =
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public static String formatForHeader(LocalDateTime dateTime) {
        return dateTime.format(HEADER_FORMATTER);
    }

    public static LocalDateTime now() {
        return LocalDateTime.now();
    }
}