package com.eschronisko.admin.users;

import com.eschronisko.account.util.UserDetailsForm;
import com.eschronisko.common.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Marcin on 11.12.2016.
 */
@Controller
@RequestMapping(value = "/admin/users")
public class UsersController {
    @Autowired
    private UsersService usersService;
    @Autowired
    private CommonService commonService;

    @RequestMapping(value = "active", method = RequestMethod.GET)
    public String getActiveUsers(Model model) {
        model.addAttribute("title", "Aktywni użytkownicy");
        model.addAttribute("usersList", usersService.getActiveUsersDetails());
        commonService.getTemplateFragments(model);
        model.addAttribute("content", "admin/manageuser/userDisplay");
        return "mainTemplate";
    }

    @RequestMapping(value = "notactive", method = RequestMethod.GET)
    public String getNotActiveUsers(Model model) {
        model.addAttribute("title", "Nieaktywni użytkownicy");
        model.addAttribute("usersList", usersService.getNotActiveUsersDetails());
        commonService.getTemplateFragments(model);
        model.addAttribute("content", "admin/manageuser/userDisplay");
        return "mainTemplate";
    }

    @RequestMapping(value = "all", method = RequestMethod.GET)
    public String getAllUsers(Model model) {
        model.addAttribute("title", "Użytkownicy");
        model.addAttribute("usersList", usersService.getAllUsersDetails());
        commonService.getTemplateFragments(model);
        model.addAttribute("content", "admin/manageuser/userDisplay");
        return "mainTemplate";
    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    @ResponseBody
    public List<UserDetailsForm> getSuggestions(@RequestParam String prefix) {
        return usersService.getUsersStartingWith(prefix);
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public String getSearchUsers(@RequestParam String prefix) {
        return "";
    }
}
