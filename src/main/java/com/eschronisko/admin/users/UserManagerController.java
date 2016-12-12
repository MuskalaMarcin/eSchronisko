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
        model.addAttribute("successLink", "/admin/activateuser/" + login);
        model.addAttribute("confirmation", "content/info/basicConfirmation");
        model.addAttribute("returnLink", "/admin/users/all");
        return "confirmationTemplate";
    }

    @RequestMapping(value = "/admin/activateuser/{login}", method = RequestMethod.POST)
    public String activateUser(@PathVariable String login) {
        usersService.checkUser(login);
        if (usersService.activateUser(login)) {
            return "redirect:/admin/users/all?success";
        } else {
            return "redirect:/admin/users/all?error";
        }
    }

    @RequestMapping(value = "/admin/deactivateuser/{login}", method = RequestMethod.GET)
    public String deactivateUserConfirmation(@PathVariable String login, Model model) {
        usersService.checkUser(login);
        model.addAttribute("msg", "Czy napewno chcesz dezaktywować konto użytkownika " + login);
        model.addAttribute("title", "Potwierdzenie dezaktywacji");
        model.addAttribute("successLink", "/admin/deactivateuser/" + login);
        model.addAttribute("confirmation", "content/info/basicConfirmation");
        model.addAttribute("returnLink", "/admin/users/all");
        return "confirmationTemplate";
    }

    @RequestMapping(value = "/admin/deactivateuser/{login}", method = RequestMethod.POST)
    public String deactivateUser(@PathVariable String login) {
        usersService.checkUser(login);
        if (usersService.deactivateUser(login)) {
            return "redirect:/admin/users/all?success";
        } else {
            return "redirect:/admin/users/all?error";
        }
    }

    @RequestMapping(value = "/admin/deleteuser/{login}", method = RequestMethod.GET)
    public String deleteUserConfirmation(@PathVariable String login, Model model) {
        usersService.checkUser(login);
        model.addAttribute("msg", "Czy napewno chcesz usunąć konto użytkownika " + login);
        model.addAttribute("title", "Potwierdzenie usunięcia");
        model.addAttribute("successLink", login);
        model.addAttribute("confirmation", "content/info/basicConfirmation");
        model.addAttribute("returnLink", "/admin/users/all");
        return "confirmationTemplate";
    }

    @RequestMapping(value = "/admin/deleteuser/{login}", method = RequestMethod.POST)
    public String deleteUser(@PathVariable String login) {
        usersService.checkUser(login);
        if (usersService.deleteUser(login)) {
            return "redirect:/admin/users/all?success";
        } else {
            return "redirect:/admin/users/all?error";
        }
    }

    @RequestMapping(value = "/admin/edituser/{login}", method = RequestMethod.GET)
    public String editUserView(@PathVariable String login, Model model) {
        usersService.checkUser(login);
        commonService.getTemplateFragments(model);
        model.addAttribute("title", "Edytuj użytkownika " + login);
        model.addAttribute("savedData", usersService.getUserData(login));
        return "admin/manageuser/userSettings";
    }

    @RequestMapping(value = "/admin/edituser", method = RequestMethod.POST)
    public String editUser(@ModelAttribute UserDetailsForm userDetailsForm) {
        usersService.checkUser(userDetailsForm.getUsername());
        if (usersService.editUser(userDetailsForm)) {
            return "redirect:/admin/users/all?success";
        } else {
            return "redirect:/admin/users/all?error";
        }
    }
}
