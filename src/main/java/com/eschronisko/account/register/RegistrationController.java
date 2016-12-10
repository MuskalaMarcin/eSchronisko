package com.eschronisko.account.register;

import com.eschronisko.account.util.UserDetailsForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Marcin on 10.12.2016.
 */
@Controller
@RequestMapping(value = "register")
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getRegistrationForm() {
        ModelMap model = new ModelMap();
        model.put("infoContent", "content/info/register");
        model.put("title", "Zarejestruj się");
        return new ModelAndView("infoTemplate", model);
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveRegistrationData(@ModelAttribute("registerForm") UserDetailsForm userDetailsForm, BindingResult result, Model model) {
        if (registrationService.register(userDetailsForm)) {

        }
        return "account/register";
    }
}
