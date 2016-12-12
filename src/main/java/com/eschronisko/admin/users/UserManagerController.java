package com.eschronisko.admin.users;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Marcin Muskala
 * @since 12.12.2016
 */
@Controller
public class UserManagerController {
    @RequestMapping(value = "/admin/activateuser/{login}", method = RequestMethod.GET)
    public String activateUser(@PathVariable String login) {
        return null;
    }

    @RequestMapping(value = "/admin/deactivateuser/{login}", method = RequestMethod.GET)
    public String deactivateUser(@PathVariable String login) {
        return null;
    }

    @RequestMapping(value = "/admin/deleteuser/{login}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable String login) {
        return null;
    }

    @RequestMapping(value = "/admin/edituser/{login}", method = RequestMethod.GET)
    public String editUser(@PathVariable String login) {
        return null;
    }
}
