package com.eschronisko.admin.report;

import com.eschronisko.database.dto.AnimalDTO;
import com.eschronisko.database.service.AnimalManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
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
    @Autowired
    private AnimalManager animalManager;

    @Override
    public List<List<Object>> getWeekReport() {
        List<List<Object>> dataTable = new ArrayList<>();
        dataTable.add(Arrays.asList("Dzień", "Przyjęcia", "Adopcje", "Różnica"));
        LocalDate today = LocalDate.now();
        List<AnimalDTO> allAnimals = animalManager.getAllEntites();
        for (LocalDate date = today.minusDays(7); date.until(today, ChronoUnit.DAYS) >= 0; date = date.plusDays(1)) {
            final LocalDate finalDate = date;
            Long acceptedNumber = allAnimals.stream().filter(animal ->
                    animal.getAcceptanceDate().toLocalDateTime().toLocalDate().isEqual(finalDate)).count();
            Long adoptedNumber = allAnimals.stream().filter(animal ->
                    animal.getAdoptionDate().toLocalDateTime().toLocalDate().isEqual(finalDate)).count();
            Long diff = acceptedNumber - adoptedNumber;
            dataTable.add(Arrays.asList(date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.forLanguageTag("pl")),
                    acceptedNumber, adoptedNumber, diff));
        }
        return dataTable;
    }

    //TODO: change logic to 
    @Override
    public List<List<Object>> getMonthReport() {
        List<List<Object>> dataTable = new ArrayList<>();
        try {
            dataTable.add(Arrays.asList("Tydzień", "Przyjęcia", "Adopcje", "Różnica"));
            WeekFields weekFields = WeekFields.of(Locale.getDefault());
            int currentWeek = LocalDate.now().get(weekFields.weekOfWeekBasedYear());
            List<AnimalDTO> allAnimals = animalManager.getAllEntites();
            for (Integer week = currentWeek; currentWeek - week < 4; week--) {
                final Integer finalWeek = week;
                Long acceptedNumber = allAnimals.stream().filter(animal ->
                        animal.getAcceptanceDate().toLocalDateTime().get(weekFields.weekOfWeekBasedYear()) == finalWeek).count();
                Long adoptedNumber = allAnimals.stream().filter(animal ->
                        animal.getAdoptionDate().toLocalDateTime().get(weekFields.weekOfWeekBasedYear()) == finalWeek).count();
                Long diff = acceptedNumber - adoptedNumber;
                dataTable.add(Arrays.asList(week.toString(), acceptedNumber, adoptedNumber, diff));
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
            int currentMonth = LocalDate.now().getMonthValue();
            List<AnimalDTO> allAnimals = animalManager.getAllEntites();
            for (Integer month = currentMonth; currentMonth - month < 12; month--) {
                final Integer finalMonth = month;
                Long acceptedNumber = allAnimals.stream().filter(animal ->
                        animal.getAcceptanceDate().toLocalDateTime().getMonthValue() == finalMonth).count();
                Long adoptedNumber = allAnimals.stream().filter(animal ->
                        animal.getAdoptionDate().toLocalDateTime().getMonthValue() == finalMonth).count();
                Long diff = acceptedNumber - adoptedNumber;
                dataTable.add(Arrays.asList(Month.of(month).getDisplayName(TextStyle.FULL, Locale.forLanguageTag("pl")),
                        acceptedNumber, adoptedNumber, diff));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataTable;
    }
}
