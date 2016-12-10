package com.eschronisko.home;

import com.eschronisko.account.util.UserRole;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && auth instanceof UsernamePasswordAuthenticationToken) {
            for (UserRole userRole : UserRole.values()) {
                if (auth.getAuthorities().stream().anyMatch(m -> m.toString().equals(userRole.toString()))) {
                    return "home/" + userRole.toString().toLowerCase();
                }
            }
        }
        return "home/index";
    }
}
