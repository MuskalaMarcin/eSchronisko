package com.eschronisko.account.settings;

import com.eschronisko.common.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Marcin on 11.12.2016.
 */
@Controller
@RequestMapping(value = "settings")
public class SettingsController {
    @Autowired
    private SettingsService settingsService;
    @Autowired
    private CommonService commonService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showCurrentSettings() {
        ModelMap model = new ModelMap();
        commonService.getTemplateFragments(model);
        model.put("content", "content/common/settings");
        model.put("title", "Ustawienia");
        return new ModelAndView("mainTemplate", model);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView saveNewSettings() {
        return null;
    }
}
