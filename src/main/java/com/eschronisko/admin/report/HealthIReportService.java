package com.eschronisko.admin.report;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Marcin Muskala
 * @since 14.12.2016
 */
@Service
public class HealthIReportService implements IReportService {


    @Override
    public List<List<Object>> getWeekReport() {
        return null;
    }

    @Override
    public List<List<Object>> getMonthReport() {
        return null;
    }

    @Override
    public List<List<Object>> getYearReport() {
        return null;
    }
}
