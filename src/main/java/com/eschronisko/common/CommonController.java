package com.eschronisko.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller for common pages.
 *
 * @author Marcin Muskala
 * @since 07.11.2016
 */
@Controller
public class CommonController {
    @Autowired
    private CommonService commonService;

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String getHomePage(Model model) {
        getCommonModelAndView("<h1>test</h1>", "Strona główna", model);
        return "staticSiteTemplate";
    }

    @RequestMapping(value = "/adoption", method = RequestMethod.GET)
    public String getAdoption(Model model) {
        getCommonModelAndView("content/common/adoption", "Zaadoptuj", model);
        return "mainTemplate";
    }

    @RequestMapping(value = "/volunteer", method = RequestMethod.GET)
    public String getVolunteer(Model model) {
        getCommonModelAndView("content/common/volunteer", "Porady", model);
        return "mainTemplate";
    }

    @RequestMapping(value = "/tips", method = RequestMethod.GET)
    public String getTips(Model model) {
        getCommonModelAndView("content/common/tips", "Porady", model);
        return "mainTemplate";
    }

    @RequestMapping(value = "/found", method = RequestMethod.GET)
    public String getFound(Model model) {
        getCommonModelAndView("content/common/found", "Znalezione", model);
        return "mainTemplate";
    }

    @RequestMapping(value = "/cooperation", method = RequestMethod.GET)
    public String getCooperation(Model model) {
        getCommonModelAndView("content/common/cooperation", "Współpraca", model);
        return "mainTemplate";
    }

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String getContact(Model model) {
        getCommonModelAndView("content/common/contact", "Kontakt", model);
        return "mainTemplate";
    }

    private void getCommonModelAndView(String contentValue, String webPageTitle, Model model) {
        commonService.getTemplateFragments(model);
        model.addAttribute("content", contentValue);
        model.addAttribute("title", webPageTitle);
    }
}
