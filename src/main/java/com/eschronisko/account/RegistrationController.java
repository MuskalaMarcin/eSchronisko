package com.eschronisko.account;

import com.eschronisko.account.util.UserDetailsForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by Marcin on 10.12.2016.
 */
@Controller
@RequestMapping(value = "register")
public class RegistrationController {
    @Autowired
    private AccountService accountService;

    @RequestMapping(method = RequestMethod.GET)
    public String getRegistrationForm(Model model) {
        model.addAttribute("infoContent", "content/info/account");
        model.addAttribute("title", "Zarejestruj się");
        return "infoTemplate";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveRegistrationData(@ModelAttribute("registerForm") @Valid UserDetailsForm userDetailsForm,
                                       BindingResult result, Model model) {
        if (!result.hasErrors() && userDetailsForm.getPassword().equals(userDetailsForm.getPasswordRepeat())
                && accountService.register(userDetailsForm)) {
            model.addAttribute("infoContent", "content/info/registerSuccess");
            model.addAttribute("title", "Status rejestracji");
        } else {
            model.addAttribute("infoContent", "content/info/registerFailed");
            model.addAttribute("title", "Błąd rejestracji");
        }
        return "infoTemplate";
    }
}
