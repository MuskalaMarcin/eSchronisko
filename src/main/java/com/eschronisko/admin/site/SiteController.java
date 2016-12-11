package com.eschronisko.admin.site;

import com.eschronisko.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Marcin on 11.12.2016.
 */
@Controller
@RequestMapping(value = "/admin/edit")
public class SiteController {
    @Autowired
    private SiteService siteService;

    @RequestMapping(value = "/{viewName}", method = RequestMethod.GET)
    public String showEditWindow(@PathVariable String viewName, Model model) {
        if (siteService.validateViewName(viewName)) {
            siteService.getSiteForEditing(viewName, model);
            return "admin/editSiteTemplate";
        } else {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value = "/{viewName}", method = RequestMethod.POST)
    public String saveChanges(@PathVariable String viewName, @RequestParam String siteContent, @RequestParam String siteTitle, Model model) {
        if (siteService.validateViewName(viewName)) {
            if (siteService.saveChanges(viewName, siteContent, siteTitle)) {
                return "redirect:/admin/edit/" + viewName + "?success";
            } else {
                return "redirect:/admin/edit/" + viewName + "?error";
            }
        } else {
            throw new ResourceNotFoundException();
        }
    }
}
