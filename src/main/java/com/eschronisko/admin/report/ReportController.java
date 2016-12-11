package com.eschronisko.admin.report;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Marcin on 11.12.2016.
 */
@Controller
@RequestMapping(value = "admin/report")
public class ReportController {
    @RequestMapping(value = "/daily", method = RequestMethod.GET)
    public String getDailyReport() {

        //TODO: add daily report
        return "Raport dzienny";
    }

    @RequestMapping(value = "/monthly", method = RequestMethod.GET)
    public String getMonthlyReport() {

        //TODO: add monthly report
        return "Raport miesieczny";
    }

    @RequestMapping(value = "/yearly", method = RequestMethod.GET)
    public String getYearlyReport() {

        //TODO: add yearly report
        return "Raport roczny";
    }
}
