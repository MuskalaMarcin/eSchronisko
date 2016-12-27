package com.eschronisko.admin.report;

import com.eschronisko.database.dto.AnimalDTO;
import com.eschronisko.database.dto.FoodRationDTO;
import com.eschronisko.database.service.FoodRationManager;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.WeekFields;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * @author Marcin Muskala
 * @since 14.12.2016
 */
@Service
public class WarehouseReportService extends ParentReportService<FoodRationDTO, FoodRationManager> implements IReportService {
    @Override
    protected void filterWeekReport(final LocalDate date, List<List<Object>> dataTable) {
        List<FoodRationDTO> foodRations = getEntitiesList();

        Long rationsNumber = foodRations.stream().mapToLong(ration -> getRationsGranted(ration, date, date)).sum();
        dataTable.add(Arrays.asList(date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.forLanguageTag("pl")),
                rationsNumber, 0D));
    }

    @Override
    public List<List<Object>> getWeekReport() {
        List<List<Object>> dataTable = getWeekReport(Arrays.asList("Dzień", "Racje żywnościowe", "Średnia"));
        calculateAverage(dataTable);
        return dataTable;
    }

    @Override
    protected void filterMonthReport(final LocalDate weekStart, final LocalDate weekEnd, List<List<Object>> dataTable) {
        List<FoodRationDTO> foodRations = getEntitiesList();
        WeekFields weekFields = WeekFields.of(Locale.getDefault());

        Long rationsNumber = foodRations.stream().mapToLong(ration -> getRationsGranted(ration, weekStart, weekEnd)).sum();
        dataTable.add(Arrays.asList(String.valueOf(weekStart.get(weekFields.weekOfWeekBasedYear())),
                rationsNumber, 0D));
    }

    @Override
    public List<List<Object>> getMonthReport() {
        List<List<Object>> dataTable = getMonthReport(Arrays.asList("Tydzień", "Racje żywnościowe", "Średnia"));
        calculateAverage(dataTable);
        return dataTable;
    }

    @Override
    protected void filterYearReport(final LocalDate monthStart, final LocalDate monthEnd, List<List<Object>> dataTable) {
        List<FoodRationDTO> foodRations = getEntitiesList();

        Long rationsNumber = foodRations.stream().mapToLong(ration -> getRationsGranted(ration, monthStart, monthEnd)).sum();
        dataTable.add(Arrays.asList(monthEnd.getMonth().getDisplayName(TextStyle.FULL_STANDALONE, Locale.forLanguageTag("pl")),
                rationsNumber, 0D));
    }

    @Override
    public List<List<Object>> getYearReport() {
        List<List<Object>> dataTable = getYearReport(Arrays.asList("Miesiąc", "Racje żywnościowe", "Średnia"));
        calculateAverage(dataTable);
        return dataTable;
    }

    public long getRationsGranted(FoodRationDTO foodRation, final LocalDate startDate, final LocalDate endDate) {
        AnimalDTO animal = foodRation.getAnimal();
        LocalDate acceptanceDate = animal.getAcceptanceDate().toLocalDateTime().toLocalDate();
        long daysNumber = 0;
        LocalDate rationsStart = (acceptanceDate.isBefore(startDate)) ? startDate : acceptanceDate;
        if (acceptanceDate.isBefore(endDate)) {
            if (animal.getAdoptionDate() == null) {
                daysNumber = rationsStart.until(endDate, ChronoUnit.DAYS);
            } else {
                LocalDate adoptionDate = animal.getAdoptionDate().toLocalDateTime().toLocalDate();
                if (adoptionDate.isAfter(endDate)) {
                    daysNumber = rationsStart.until(endDate, ChronoUnit.DAYS);
                } else {
                    daysNumber = rationsStart.until(adoptionDate, ChronoUnit.DAYS);
                }
            }
            daysNumber = Math.abs(daysNumber);
            if (startDate.isEqual(endDate)) daysNumber++;
        }

        return daysNumber * foodRation.getAmount();
    }
}
