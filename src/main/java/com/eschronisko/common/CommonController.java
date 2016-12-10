package com.eschronisko.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getHomePage() {
        return getCommonModelAndView("content/common/home", "Strona główna");
    }

    @RequestMapping(value = "/adoption", method = RequestMethod.GET)
    public ModelAndView getAdoption() {
        return getCommonModelAndView("content/common/adoption", "Zaadoptuj");
    }

    @RequestMapping(value = "/volunteer", method = RequestMethod.GET)
    public ModelAndView getVolunteer() {
        return getCommonModelAndView("content/common/volunteer", "Porady");
    }

    @RequestMapping(value = "/tips", method = RequestMethod.GET)
    public ModelAndView getTips() {
        return getCommonModelAndView("content/common/tips", "Porady");
    }

    @RequestMapping(value = "/found", method = RequestMethod.GET)
    public ModelAndView getFound() {
        return getCommonModelAndView("content/common/found", "Znalezione");
    }

    @RequestMapping(value = "/cooperation", method = RequestMethod.GET)
    public ModelAndView getCooperation() {
        return getCommonModelAndView("content/common/cooperation", "Współpraca");
    }

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public ModelAndView getContact() {
        return getCommonModelAndView("content/common/contact", "Kontakt");
    }

    private ModelAndView getCommonModelAndView(String contentPath, String webPageTitle) {
        ModelMap model = new ModelMap();
        commonService.getTemplateFragments(model);
        model.put("content", contentPath);
        model.put("title", webPageTitle);
        return new ModelAndView("mainTemplate", model);
    }
}
