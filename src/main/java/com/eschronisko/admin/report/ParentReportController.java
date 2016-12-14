package com.eschronisko.admin.report;

import com.eschronisko.common.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Marcin Muskala
 * @since 14.12.2016
 */
public abstract class ParentReportController {
    @Autowired
    private CommonService commonService;
    private IReportService reportService;

    protected ParentReportController(IReportService reportService) {
        this.reportService = reportService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public abstract String getReportPage(Model model);

    protected String getCommonReportFields(Model model) {
        commonService.getTemplateFragments(model);
        return "mainTemplate";
    }

    @RequestMapping(value = "week", method = RequestMethod.GET)
    @ResponseBody
    public List<List<Object>> getWeekReport() {
        return reportService.getWeekReport();
    }

    @RequestMapping(value = "month", method = RequestMethod.GET)
    @ResponseBody
    public List<List<Object>> getMonthReport() {
        return reportService.getMonthReport();
    }

    @RequestMapping(value = "year", method = RequestMethod.GET)
    @ResponseBody
    public List<List<Object>> getYearReport() {
        return reportService.getYearReport();
    }
}
