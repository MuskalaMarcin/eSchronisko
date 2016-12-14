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
@RequestMapping(value = "admin/report/health")
public class HealthReportController extends ParentReportController {
    @Autowired
    public HealthReportController(HealthIReportService healthReportService) {
        super(healthReportService);
    }

    @Override
    public String getReportPage(Model model) {
        model.addAttribute("content", "admin/report/healthReport");
        model.addAttribute("title", "Raport stanu zdrowia");
        return getCommonReportFields(model);
    }
}
