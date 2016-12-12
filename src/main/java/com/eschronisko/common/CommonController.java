package com.eschronisko.common;

import com.eschronisko.admin.site.SiteService;
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
    private SiteService siteService;

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String getHomePage(Model model) {
        siteService.getSiteWithModelAndView("home", model);
        return "staticSiteTemplate";
    }

    @RequestMapping(value = "/adoption", method = RequestMethod.GET)
    public String getAdoption(Model model) {
        siteService.getSiteWithModelAndView("adoption", model);
        return "staticSiteTemplate";
    }

    @RequestMapping(value = "/volunteer", method = RequestMethod.GET)
    public String getVolunteer(Model model) {
        siteService.getSiteWithModelAndView("volunteer", model);
        return "staticSiteTemplate";
    }

    @RequestMapping(value = "/tips", method = RequestMethod.GET)
    public String getTips(Model model) {
        siteService.getSiteWithModelAndView("tips", model);
        return "staticSiteTemplate";
    }

    @RequestMapping(value = "/found", method = RequestMethod.GET)
    public String getFound(Model model) {
        siteService.getSiteWithModelAndView("found", model);
        return "staticSiteTemplate";
    }

    @RequestMapping(value = "/cooperation", method = RequestMethod.GET)
    public String getCooperation(Model model) {
        siteService.getSiteWithModelAndView("cooperation", model);
        return "staticSiteTemplate";
    }

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String getContact(Model model) {
        siteService.getSiteWithModelAndView("contact", model);
        return "staticSiteTemplate";
    }

    @RequestMapping(value = "/404", method = RequestMethod.GET)
    public String show404() {
        return "404";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String show403() {
        return "403";
    }
}
