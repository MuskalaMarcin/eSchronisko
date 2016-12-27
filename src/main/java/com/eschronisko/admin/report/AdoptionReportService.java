package com.eschronisko.admin.report;

import com.eschronisko.database.dto.AnimalDTO;
import com.eschronisko.database.service.AnimalManager;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.time.temporal.WeekFields;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * @author Marcin Muskala
 * @since 14.12.2016
 */
@Service
public class AdoptionReportService extends ParentReportService<AnimalDTO, AnimalManager> implements IReportService {
    @Override
    protected void filterWeekReport(final LocalDate date, List<List<Object>> dataTable) {
        List<AnimalDTO> allAnimals = getEntitiesList();
        Long acceptedNumber = allAnimals.stream().filter(animal ->
                animal.getAcceptanceDate().toLocalDateTime().toLocalDate().isEqual(date)).count();
        Long adoptedNumber = allAnimals.stream().filter(animal -> animal.getAdoptionDate() != null &&
                animal.getAdoptionDate().toLocalDateTime().toLocalDate().isEqual(date)).count();
        Long diff = acceptedNumber - adoptedNumber;
        dataTable.add(Arrays.asList(date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.forLanguageTag("pl")),
                acceptedNumber, adoptedNumber, diff));
    }

    @Override
    public List<List<Object>> getWeekReport() {
        return getWeekReport(Arrays.asList("Dzień", "Przyjęcia", "Adopcje", "Różnica"));
    }

    @Override
    protected void filterMonthReport(final LocalDate weekStart, final LocalDate weekEnd, List<List<Object>> dataTable) {
        List<AnimalDTO> allAnimals = getEntitiesList();
        WeekFields weekFields = WeekFields.of(Locale.getDefault());

        Long acceptedNumber = allAnimals.stream().filter(animal ->
                TimeUtils.isBetween(weekStart, weekEnd, animal.getAcceptanceDate())).count();
        Long adoptedNumber = allAnimals.stream().filter(animal -> animal.getAdoptionDate() != null &&
                TimeUtils.isBetween(weekStart, weekEnd, animal.getAdoptionDate())).count();
        Long diff = acceptedNumber - adoptedNumber;
        dataTable.add(Arrays.asList(String.valueOf(weekStart.get(weekFields.weekOfWeekBasedYear())), acceptedNumber, adoptedNumber, diff));
    }

    @Override
    public List<List<Object>> getMonthReport() {
        return getMonthReport(Arrays.asList("Tydzień", "Przyjęcia", "Adopcje", "Różnica"));
    }

    @Override
    protected void filterYearReport(final LocalDate monthStart, final LocalDate monthEnd, List<List<Object>> dataTable) {
        List<AnimalDTO> allAnimals = getEntitiesList();

        Long acceptedNumber = allAnimals.stream().filter(animal ->
                TimeUtils.isBetween(monthStart, monthEnd, animal.getAcceptanceDate())).count();
        Long adoptedNumber = allAnimals.stream().filter(animal -> animal.getAdoptionDate() != null &&
                TimeUtils.isBetween(monthStart, monthEnd, animal.getAdoptionDate())).count();
        Long diff = acceptedNumber - adoptedNumber;
        dataTable.add(Arrays.asList(monthEnd.getMonth().getDisplayName(TextStyle.FULL_STANDALONE, Locale.forLanguageTag("pl")),
                acceptedNumber, adoptedNumber, diff));
    }

    @Override
    public List<List<Object>> getYearReport() {
        return getYearReport(Arrays.asList("Miesiąc", "Przyjęcia", "Adopcje", "Różnica"));
    }
}
