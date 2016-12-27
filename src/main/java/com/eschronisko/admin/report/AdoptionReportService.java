package com.eschronisko.admin.report;

import com.eschronisko.database.dto.AnimalDTO;
import com.eschronisko.database.service.AnimalManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * @author Marcin Muskala
 * @since 14.12.2016
 */
@Service
public class AdoptionReportService implements IReportService {
    private List<AnimalDTO> animalsList;
    private LocalDateTime lastUpdateTime;

    @Autowired
    private AnimalManager animalManager;

    private synchronized List<AnimalDTO> getAllAnimals() {
        if (animalsList == null || lastUpdateTime.until(LocalDateTime.now(), ChronoUnit.SECONDS) > 10) {
            animalsList = animalManager.getAllEntites();
            lastUpdateTime = LocalDateTime.now();
        }
        return animalsList;
    }

    @Override
    public List<List<Object>> getWeekReport() {
        List<List<Object>> dataTable = new ArrayList<>();
        dataTable.add(Arrays.asList("Dzień", "Przyjęcia", "Adopcje", "Różnica"));
        LocalDate today = LocalDate.now();
        List<AnimalDTO> allAnimals = getAllAnimals();
        for (LocalDate date = today.minusDays(7); date.until(today, ChronoUnit.DAYS) >= 0; date = date.plusDays(1)) {
            final LocalDate finalDate = date;
            Long acceptedNumber = allAnimals.stream().filter(animal ->
                    animal.getAcceptanceDate().toLocalDateTime().toLocalDate().isEqual(finalDate)).count();
            Long adoptedNumber = allAnimals.stream().filter(animal -> animal.getAdoptionDate() != null &&
                    animal.getAdoptionDate().toLocalDateTime().toLocalDate().isEqual(finalDate)).count();
            Long diff = acceptedNumber - adoptedNumber;
            dataTable.add(Arrays.asList(date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.forLanguageTag("pl")),
                    acceptedNumber, adoptedNumber, diff));
        }
        return dataTable;
    }

    @Override
    public List<List<Object>> getMonthReport() {
        List<List<Object>> dataTable = new ArrayList<>();
        try {
            dataTable.add(Arrays.asList("Tydzień", "Przyjęcia", "Adopcje", "Różnica"));
            LocalDate weekEnd = LocalDate.now().with(ChronoField.DAY_OF_WEEK, 7).minusWeeks(3);
            LocalDate weekStart = LocalDate.now().with(ChronoField.DAY_OF_WEEK, 1).minusWeeks(3);
            WeekFields weekFields = WeekFields.of(Locale.getDefault());

            List<AnimalDTO> allAnimals = getAllAnimals();
            for (int i = 0; i < 4; weekEnd = weekEnd.plusWeeks(1), weekStart = weekStart.plusWeeks(1), i++) {
                final LocalDate finalWeekStart = weekStart;
                final LocalDate finalWeekEnd = weekEnd;

                Long acceptedNumber = allAnimals.stream().filter(animal ->
                        TimeUtils.isBetween(finalWeekStart, finalWeekEnd, animal.getAcceptanceDate())).count();
                Long adoptedNumber = allAnimals.stream().filter(animal -> animal.getAdoptionDate() != null &&
                        TimeUtils.isBetween(finalWeekStart, finalWeekEnd, animal.getAdoptionDate())).count();
                Long diff = acceptedNumber - adoptedNumber;
                dataTable.add(Arrays.asList(String.valueOf(finalWeekStart.get(weekFields.weekOfWeekBasedYear())), acceptedNumber, adoptedNumber, diff));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataTable;
    }

    @Override
    public List<List<Object>> getYearReport() {
        List<List<Object>> dataTable = new ArrayList<>();
        try {
            dataTable.add(Arrays.asList("Miesiąc", "Przyjęcia", "Adopcje", "Różnica"));

            LocalDate monthStart = LocalDate.now().withDayOfMonth(1).minusMonths(11);
            LocalDate monthEnd = LocalDate.now().withDayOfMonth(31).minusMonths(11);

            List<AnimalDTO> allAnimals = getAllAnimals();
            for (int i = 0; i < 12; monthStart = monthStart.plusMonths(1), monthEnd = monthEnd.plusMonths(1), i++) {
                final LocalDate finalMonthStart = monthStart;
                final LocalDate finalMonthEnd = monthEnd;

                Long acceptedNumber = allAnimals.stream().filter(animal ->
                        TimeUtils.isBetween(finalMonthStart, finalMonthEnd, animal.getAcceptanceDate())).count();
                Long adoptedNumber = allAnimals.stream().filter(animal -> animal.getAdoptionDate() != null &&
                        TimeUtils.isBetween(finalMonthStart, finalMonthEnd, animal.getAdoptionDate())).count();
                Long diff = acceptedNumber - adoptedNumber;
                dataTable.add(Arrays.asList(finalMonthEnd.getMonth().getDisplayName(TextStyle.FULL_STANDALONE, Locale.forLanguageTag("pl")),
                        acceptedNumber, adoptedNumber, diff));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataTable;
    }
}
