package com.eschronisko.admin.users;

import com.eschronisko.account.util.UserDetailsForm;
import com.eschronisko.common.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;

/**
 * @author Marcin Muskala
 * @since 12.12.2016
 */
@Controller
public class UserManagerController {
    @Autowired
    private UsersService usersService;
    @Autowired
    private CommonService commonService;

    @RequestMapping(value = "/admin/activateuser/{login}", method = RequestMethod.GET)
    public String activateUserConfirmation(@PathVariable String login, Model model) {
        usersService.checkUser(login);
        model.addAttribute("msg", "Czy napewno chcesz aktywować konto użytkownika " + login);
        model.addAttribute("title", "Potwierdzenie aktywacji");
        model.addAttribute("infoContent", "content/info/basicConfirmation");
        model.addAttribute("successLink", "/admin/activateuser/" + login);
        model.addAttribute("confirmation", "content/info/basicConfirmation");
        model.addAttribute("returnLink", "/admin/users/all");
        return "infoTemplate";
    }

    @RequestMapping(value = "/admin/activateuser/{login}", method = RequestMethod.POST)
    public String activateUser(@PathVariable String login) {
        usersService.checkUser(login);
        if (usersService.activateUser(login)) {
            return "redirect:/admin/showuser/" + login + "?success";
        } else {
            return "redirect:/admin/showuser/" + login + "?error";
        }
    }

    @RequestMapping(value = "/admin/deactivateuser/{login}", method = RequestMethod.GET)
    public String deactivateUserConfirmation(@PathVariable String login, Model model) {
        usersService.checkUser(login);
        model.addAttribute("msg", "Czy napewno chcesz dezaktywować konto użytkownika " + login);
        model.addAttribute("title", "Potwierdzenie dezaktywacji");
        model.addAttribute("infoContent", "content/info/basicConfirmation");
        model.addAttribute("successLink", "/admin/deactivateuser/" + login);
        model.addAttribute("confirmation", "content/info/basicConfirmation");
        model.addAttribute("returnLink", "/admin/users/all");
        return "infoTemplate";
    }

    @RequestMapping(value = "/admin/deactivateuser/{login}", method = RequestMethod.POST)
    public String deactivateUser(@PathVariable String login) {
        usersService.checkUser(login);
        if (usersService.deactivateUser(login)) {
            return "redirect:/admin/showuser/" + login + "?success";
        } else {
            return "redirect:/admin/showuser/" + login + "?error";
        }
    }

    @RequestMapping(value = "/admin/deleteuser/{login}", method = RequestMethod.GET)
    public String deleteUserConfirmation(@PathVariable String login, Model model) {
        usersService.checkUser(login);
        model.addAttribute("msg", "Czy napewno chcesz usunąć konto użytkownika " + login);
        model.addAttribute("title", "Potwierdzenie usunięcia");
        model.addAttribute("successLink", login);
        model.addAttribute("infoContent", "content/info/basicConfirmation");
        model.addAttribute("confirmation", "content/info/basicConfirmation");
        model.addAttribute("returnLink", "/admin/users/all");
        return "infoTemplate";
    }

    @RequestMapping(value = "/admin/deleteuser/{login}", method = RequestMethod.POST)
    public String deleteUser(@PathVariable String login) {
        usersService.checkUser(login);
        if (usersService.deleteUser(login)) {
            return "redirect:/admin/showuser/" + login + "?success";
        } else {
            return "redirect:/admin/showuser/" + login + "?error";
        }
    }

    @RequestMapping(value = "/admin/edituser/{login}", method = RequestMethod.GET)
    public String editUserView(@PathVariable String login, Model model) {
        usersService.checkUser(login);
        commonService.getTemplateFragments(model);
        model.addAttribute("content", "admin/manageuser/userSettings");
        model.addAttribute("title", "Edytuj użytkownika " + login);
        model.addAttribute("savedData", usersService.getUserData(login));
        return "mainTemplate";
    }

    @RequestMapping(value = "/admin/edituser", method = RequestMethod.POST)
    public String editUser(@ModelAttribute UserDetailsForm userDetailsForm) {
        usersService.checkUser(userDetailsForm.getUsername());
        if (usersService.editUser(userDetailsForm)) {
            return "redirect:/admin/showuser/" + userDetailsForm.getUsername() + "?success";
        } else {
            return "redirect:/admin/showuser/" + userDetailsForm.getUsername() + "?error";
        }
    }

    @RequestMapping(value = "/admin/showuser/{login}", method = RequestMethod.GET)
    public String showUser(@PathVariable String login, Model model) {
        usersService.checkUser(login);
        model.addAttribute("title", "Użytkownik " + login);
        model.addAttribute("content", "admin/manageuser/userDisplay");
        model.addAttribute("usersList", Arrays.asList(usersService.getUserData(login)));
        commonService.getTemplateFragments(model);
        return "mainTemplate";
    }
}
