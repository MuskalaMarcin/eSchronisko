package com.eschronisko.admin.report;

import com.eschronisko.database.dto.MedicalTreatmentDTO;
import com.eschronisko.database.service.MedicalTreatmentManager;
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
public class HealthIReportService extends ParentReportService<MedicalTreatmentDTO, MedicalTreatmentManager> {
    @Override
    protected void filterWeekReport(final LocalDate date, List<List<Object>> dataTable) {
        List<MedicalTreatmentDTO> allMedicalTreatments = getEntitiesList();
        Long medicalTreatmentsNumber = allMedicalTreatments.stream().filter(medicalTreatmentDTO ->
                medicalTreatmentDTO.getEndDate().toLocalDateTime().toLocalDate().isEqual(date)).count();
        dataTable.add(Arrays.asList(date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.forLanguageTag("pl")),
                medicalTreatmentsNumber, 0D));
    }

    @Override
    public List<List<Object>> getWeekReport() {
        List<List<Object>> dataTable = getWeekReport(Arrays.asList("Dzień", "Ilość zabiegów", "Średnia"));
        calculateAverage(dataTable);
        return dataTable;
    }

    @Override
    protected void filterMonthReport(final LocalDate weekStart, final LocalDate weekEnd, List<List<Object>> dataTable) {
        List<MedicalTreatmentDTO> allMedicalTreatments = getEntitiesList();
        WeekFields weekFields = WeekFields.of(Locale.getDefault());

        Long medicalTreatmentsNumber = allMedicalTreatments.stream().filter(medicalTreatmentDTO ->
                TimeUtils.isBetween(weekStart, weekEnd, medicalTreatmentDTO.getEndDate())).count();
        dataTable.add(Arrays.asList(String.valueOf(weekStart.get(weekFields.weekOfWeekBasedYear())),
                medicalTreatmentsNumber, 0D));
    }

    @Override
    public List<List<Object>> getMonthReport() {
        List<List<Object>> dataTable = getMonthReport(Arrays.asList("Tydzień", "Ilość zabiegów", "Średnia"));
        calculateAverage(dataTable);
        return dataTable;
    }

    @Override
    protected void filterYearReport(final LocalDate monthStart, final LocalDate monthEnd, List<List<Object>> dataTable) {
        List<MedicalTreatmentDTO> allMedicalTreatments = getEntitiesList();

        Long medicalTreatmentsNumber = allMedicalTreatments.stream().filter(medicalTreatmentDTO ->
                TimeUtils.isBetween(monthStart, monthEnd, medicalTreatmentDTO.getEndDate())).count();
        dataTable.add(Arrays.asList(monthEnd.getMonth().getDisplayName(TextStyle.FULL_STANDALONE, Locale.forLanguageTag("pl")),
                medicalTreatmentsNumber, 0D));
    }

    @Override
    public List<List<Object>> getYearReport() {
        List<List<Object>> dataTable = getYearReport(Arrays.asList("Miesiąc", "Ilość zabiegów", "Średnia"));
        calculateAverage(dataTable);
        return dataTable;
    }
}
