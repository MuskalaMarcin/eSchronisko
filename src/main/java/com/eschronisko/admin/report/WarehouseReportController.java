package com.eschronisko.admin.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Marcin Muskala
 * @since 14.12.2016
 */
@Controller
@RequestMapping(value = "admin/report/warehouse")
public class WarehouseReportController extends ParentReportController {
    @Autowired
    public WarehouseReportController(WarehouseReportService warehouseReportService) {
        super(warehouseReportService);
    }

    @Override
    public String getReportPage(Model model) {
        model.addAttribute("content", "admin/report/warehouseReport");
        model.addAttribute("title", "Raport magazynu");
        return getCommonReportFields(model);
    }
}
