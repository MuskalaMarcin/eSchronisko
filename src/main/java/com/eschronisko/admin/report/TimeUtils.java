package com.eschronisko.admin.report;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by Marcin on 27.12.2016.
 */
public class TimeUtils {
    public static boolean isBetween(LocalDate startDate, LocalDate endDate, LocalDate checkedDate) {
        return (checkedDate.isEqual(startDate) || checkedDate.isAfter(startDate))
                && (checkedDate.isEqual(endDate) || checkedDate.isBefore(endDate));
    }

    public static boolean isBetween(LocalDateTime startDate, LocalDateTime endDate, LocalDateTime checkedDate) {
        return isBetween(startDate.toLocalDate(), endDate.toLocalDate(), checkedDate.toLocalDate());
    }

    public static boolean isBetween(LocalDate startDate, LocalDate endDate, Timestamp checkedDate) {
        return isBetween(startDate, endDate, checkedDate.toLocalDateTime().toLocalDate());
    }
}
