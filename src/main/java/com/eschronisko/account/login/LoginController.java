package com.eschronisko.account.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Marcin on 10.12.2016.
 */
@Controller
@RequestMapping(value = "login")
public class LoginController {
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getLoginPage() {
        ModelMap model = new ModelMap();
        model.put("infoContent", "content/info/loginPage");
        model.put("title", "Zaloguj się");
        return new ModelAndView("infoTemplate", model);
    }
}
