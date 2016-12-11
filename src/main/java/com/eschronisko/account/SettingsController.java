package com.eschronisko.account;

import com.eschronisko.account.util.UserDetailsForm;
import com.eschronisko.common.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by Marcin on 11.12.2016.
 */
@Controller
@RequestMapping(value = "settings")
public class SettingsController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private CommonService commonService;

    @RequestMapping(method = RequestMethod.GET)
    public String showCurrentSettings(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        commonService.getTemplateFragments(model);
        model.addAttribute("content", "content/common/settings");
        model.addAttribute("title", "Ustawienia");
        model.addAttribute("savedData", accountService.getCurrentValues(auth));
        return "mainTemplate";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveNewSettings(@ModelAttribute("registerForm") @Valid UserDetailsForm userDetailsForm,
                                  BindingResult result) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!result.hasErrors() && userDetailsForm.getPassword().equals(userDetailsForm.getPasswordRepeat())
                && accountService.updateSettings(userDetailsForm, auth)) {
            return "redirect:/settings?saved";
        } else {
            return "redirect:/settings?error";
        }
    }
}
