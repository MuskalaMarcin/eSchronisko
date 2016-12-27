package com.eschronisko.admin.report;

import com.eschronisko.database.dto.ParentDTO;
import com.eschronisko.database.service.ParentManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin on 27.12.2016.
 */
public abstract class ParentReportService<A extends ParentDTO, B extends ParentManager> implements IReportService {
    private List<A> DTOsList;
    private LocalDateTime lastUpdateTime;

    @Autowired
    protected B manager;

    protected abstract void filterWeekReport(final LocalDate date, List<List<Object>> dataTable);

    protected abstract void filterMonthReport(final LocalDate weekStart, final LocalDate weekEnd, List<List<Object>> dataTable);

    protected abstract void filterYearReport(final LocalDate monthStart, final LocalDate monthEnd, List<List<Object>> dataTable);

    protected synchronized List<A> getEntitiesList() {
        if (DTOsList == null || lastUpdateTime.until(LocalDateTime.now(), ChronoUnit.SECONDS) > 10) {
            DTOsList = manager.getAllEntites();
            lastUpdateTime = LocalDateTime.now();
        }
        return DTOsList;
    }

    public List<List<Object>> getWeekReport(List<Object> labels) {
        List<List<Object>> dataTable = new ArrayList<>();
        dataTable.add(labels);
        LocalDate today = LocalDate.now();
        for (LocalDate date = today.minusDays(7); date.until(today, ChronoUnit.DAYS) >= 0; date = date.plusDays(1)) {
            filterWeekReport(date, dataTable);
        }
        return dataTable;
    }

    public List<List<Object>> getMonthReport(List<Object> labels) {
        List<List<Object>> dataTable = new ArrayList<>();
        try {
            dataTable.add(labels);
            LocalDate weekEnd = LocalDate.now().with(ChronoField.DAY_OF_WEEK, 7).minusWeeks(3);
            LocalDate weekStart = LocalDate.now().with(ChronoField.DAY_OF_WEEK, 1).minusWeeks(3);
            for (int i = 0; i < 4; weekEnd = weekEnd.plusWeeks(1), weekStart = weekStart.plusWeeks(1), i++) {
                filterMonthReport(weekStart, weekEnd, dataTable);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataTable;
    }

    public List<List<Object>> getYearReport(List<Object> labels) {
        List<List<Object>> dataTable = new ArrayList<>();
        try {
            dataTable.add(labels);

            LocalDate monthStart = LocalDate.now().withDayOfMonth(1).minusMonths(11);
            LocalDate monthEnd = LocalDate.now().withDayOfMonth(31).minusMonths(11);

            for (int i = 0; i < 12; monthStart = monthStart.plusMonths(1), monthEnd = monthEnd.plusMonths(1), i++) {
                filterYearReport(monthStart, monthEnd, dataTable);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataTable;
    }

    protected void calculateAverage(List<List<Object>> dataTable) {
        Double average = 0D;
        for (int i = 1; i < dataTable.size(); i++) {
            average += (Long) dataTable.get(i).get(1);
        }
        average /= (dataTable.size() - 1D);
        for (int i = 1; i < dataTable.size(); i++) {
            dataTable.get(i).set(2, average);
        }
    }
}
