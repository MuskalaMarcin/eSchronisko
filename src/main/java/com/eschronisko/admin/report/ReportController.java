package com.eschronisko.admin.report;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Marcin on 11.12.2016.
 */
@Controller
public class ReportController {
    @RequestMapping(value = "admin/report/daily", method = RequestMethod.GET)
    public String getDailyReport() {
        System.out.println("tutaj");
        return "Raport dzienny";
    }
}
