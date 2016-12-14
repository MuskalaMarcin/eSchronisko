package com.eschronisko.admin.report;

import com.eschronisko.database.service.WarehouseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Marcin Muskala
 * @since 14.12.2016
 */
@Service
public class WarehouseReportService implements IReportService {
    @Autowired
    private WarehouseManager warehouseManager;

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
