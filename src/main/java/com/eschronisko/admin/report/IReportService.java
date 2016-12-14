package com.eschronisko.admin.report;

import java.util.List;

/**
 * @author Marcin Muskala
 * @since 14.12.2016
 */
public interface IReportService {
    List<List<Object>> getWeekReport();

    List<List<Object>> getMonthReport();

    List<List<Object>> getYearReport();
}
