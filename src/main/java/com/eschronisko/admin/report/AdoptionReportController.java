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
@RequestMapping(value = "admin/report/adoption")
public class AdoptionReportController extends ParentReportController {
    @Autowired
    public AdoptionReportController(AdoptionReportService adoptionReportService) {
        super(adoptionReportService);
    }

    @Override
    public String getReportPage(Model model) {
        model.addAttribute("content", "admin/report/adoptionReport");
        model.addAttribute("title", "Raport adopcji");
        return getCommonReportFields(model);
    }
}
