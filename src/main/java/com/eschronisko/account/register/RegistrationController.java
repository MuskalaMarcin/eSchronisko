package com.eschronisko.account.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Marcin on 10.12.2016.
 */
@Controller
@RequestMapping(value = "register")
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;

    @RequestMapping(method = RequestMethod.GET)
    public String getRegistrationForm(){
        return "account/register";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveRegistrationData(@ModelAttribute("registerForm") RegistrationForm registrationForm, BindingResult result, Model model){
        if(registrationService.register(registrationForm)){

        }
        return "account/register";
    }
}
