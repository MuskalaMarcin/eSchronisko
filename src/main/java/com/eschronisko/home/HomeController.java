package com.eschronisko.home;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller for homepage.
 *
 * @author Marcin Muskala
 * @since 07.11.2016
 */
@Controller
public class HomeController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String redirectToHome() {
        System.out.println("isAuthenticated: " + SecurityContextHolder.getContext().getAuthentication().isAuthenticated());
        return "index";
    }
}
