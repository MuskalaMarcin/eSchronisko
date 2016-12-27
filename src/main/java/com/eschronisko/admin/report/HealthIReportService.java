package com.eschronisko.admin.report;

import com.eschronisko.database.dto.MedicalTreatmentDTO;
import com.eschronisko.database.service.MedicalTreatmentManager;
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
public class HealthIReportService implements IReportService {
    private List<MedicalTreatmentDTO> medicalTreatmentDTOList;
    private LocalDateTime lastUpdateTime;

    @Autowired
    private MedicalTreatmentManager medicalTreatmentManager;

    private synchronized List<MedicalTreatmentDTO> getAllMedicalTreatments() {
        if (medicalTreatmentDTOList == null || lastUpdateTime.until(LocalDateTime.now(), ChronoUnit.SECONDS) > 10) {
            medicalTreatmentDTOList = medicalTreatmentManager.getAllEntites();
            lastUpdateTime = LocalDateTime.now();
        }
        return medicalTreatmentDTOList;
    }

    @Override
    public List<List<Object>> getWeekReport() {
        List<List<Object>> dataTable = new ArrayList<>();
        try {
            dataTable.add(Arrays.asList("Dzień", "Ilość zabiegów", "Średnia"));
            LocalDate today = LocalDate.now();
            List<MedicalTreatmentDTO> allMedicalTreatments = getAllMedicalTreatments();
            for (LocalDate date = today.minusDays(7); date.until(today, ChronoUnit.DAYS) >= 0; date = date.plusDays(1)) {
                final LocalDate finalDate = date;
                Long medicalTreatmentsNumber = allMedicalTreatments.stream().filter(medicalTreatmentDTO ->
                        medicalTreatmentDTO.getEndDate().toLocalDateTime().toLocalDate().isEqual(finalDate)).count();
                dataTable.add(Arrays.asList(date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.forLanguageTag("pl")),
                        medicalTreatmentsNumber, 0D));
            }
            calculateAverage(dataTable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataTable;
    }

    @Override
    public List<List<Object>> getMonthReport() {
        List<List<Object>> dataTable = new ArrayList<>();
        try {
            dataTable.add(Arrays.asList("Tydzień", "Ilość zabiegów", "Średnia"));
            LocalDate weekEnd = LocalDate.now().with(ChronoField.DAY_OF_WEEK, 7).minusWeeks(3);
            LocalDate weekStart = LocalDate.now().with(ChronoField.DAY_OF_WEEK, 1).minusWeeks(3);
            WeekFields weekFields = WeekFields.of(Locale.getDefault());

            List<MedicalTreatmentDTO> allMedicalTreatments = getAllMedicalTreatments();
            for (int i = 0; i < 4; weekEnd = weekEnd.plusWeeks(1), weekStart = weekStart.plusWeeks(1), i++) {
                final LocalDate finalWeekStart = weekStart;
                final LocalDate finalWeekEnd = weekEnd;

                Long medicalTreatmentsNumber = allMedicalTreatments.stream().filter(medicalTreatmentDTO ->
                        TimeUtils.isBetween(finalWeekStart, finalWeekEnd, medicalTreatmentDTO.getEndDate())).count();
                dataTable.add(Arrays.asList(String.valueOf(finalWeekStart.get(weekFields.weekOfWeekBasedYear())),
                        medicalTreatmentsNumber, 0D));
            }
            calculateAverage(dataTable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataTable;
    }

    @Override
    public List<List<Object>> getYearReport() {
        List<List<Object>> dataTable = new ArrayList<>();
        try {
            dataTable.add(Arrays.asList("Tydzień", "Ilość zabiegów", "Średnia"));

            LocalDate monthStart = LocalDate.now().withDayOfMonth(1).minusMonths(11);
            LocalDate monthEnd = LocalDate.now().withDayOfMonth(31).minusMonths(11);

            List<MedicalTreatmentDTO> allMedicalTreatments = getAllMedicalTreatments();
            for (int i = 0; i < 12; monthStart = monthStart.plusMonths(1), monthEnd = monthEnd.plusMonths(1), i++) {
                final LocalDate finalMonthStart = monthStart;
                final LocalDate finalMonthEnd = monthEnd;

                Long medicalTreatmentsNumber = allMedicalTreatments.stream().filter(medicalTreatmentDTO ->
                        TimeUtils.isBetween(finalMonthStart, finalMonthEnd, medicalTreatmentDTO.getEndDate())).count();
                dataTable.add(Arrays.asList(finalMonthEnd.getMonth().getDisplayName(TextStyle.FULL_STANDALONE, Locale.forLanguageTag("pl")),
                        medicalTreatmentsNumber, 0D));
            }
            calculateAverage(dataTable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataTable;
    }

    private void calculateAverage(List<List<Object>> dataTable) {
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
