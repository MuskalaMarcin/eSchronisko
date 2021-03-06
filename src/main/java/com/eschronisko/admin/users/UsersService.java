package com.eschronisko.admin.users;

import com.eschronisko.account.AccountService;
import com.eschronisko.account.util.UserDetailsForm;
import com.eschronisko.common.Page;
import com.eschronisko.database.dto.AppUserDTO;
import com.eschronisko.database.service.AppUserManager;
import com.eschronisko.exception.ResourceNotFoundException;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Marcin Muskala
 * @since 11.12.2016
 */
@Service
public class UsersService {
    @Autowired
    private AccountService accountService;
    @Autowired
    private AppUserManager appUserManager;

    public Page<UserDetailsForm> getActiveUsersDetails(Integer pageNumber) {
        return convertPageToUserDetailsPage(appUserManager.getActiveUsers(pageNumber));
    }

    public Page<UserDetailsForm> getNotActiveUsersDetails(Integer pageNumber) {
        return convertPageToUserDetailsPage(appUserManager.getNotActiveUsers(pageNumber));
    }

    public List<UserDetailsForm> getUsersStartingWith(final String prefix) {
        return appUserManager.getAllEntites().stream().map(accountService::getUserDetails).filter(f -> {
            if (prefix.isEmpty()) {
                return false;
            } else if (prefix.contains(" ")) {
                boolean result = true;
                for (String splitted : prefix.split(" ")) {
                    result &= isStartingWith(f, splitted);
                }
                return result;
            } else {
                return isStartingWith(f, prefix);
            }
        }).collect(Collectors.toList());
    }

    private boolean isStartingWith(UserDetailsForm user, String prefix) {
        return StringUtils.startsWithIgnoreCase(user.getName(), prefix)
                || StringUtils.startsWithIgnoreCase(user.getSurname(), prefix)
                || StringUtils.startsWithIgnoreCase(user.getUsername(), prefix);
    }

    private Page<UserDetailsForm> convertPageToUserDetailsPage(Page<AppUserDTO> usersPage) {
        List<UserDetailsForm> userDetails = usersPage.getContent().stream().map(accountService::getUserDetails).collect(Collectors.toList());
        return new Page<>(usersPage.getPageNumber(), usersPage.getTotalPages(), userDetails);
    }

    public Page<UserDetailsForm> getAllUsersDetails(Integer pageNumber) {
        return convertPageToUserDetailsPage(appUserManager.getAllEntites(pageNumber));
    }

    public boolean activateUser(String login) {
        try {
            AppUserDTO user = appUserManager.getWithId(login);
            user.setIsActive(1);
            appUserManager.updateEntity(user);
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deactivateUser(String login) {
        try {
            AppUserDTO user = appUserManager.getWithId(login);
            user.setIsActive(0);
            appUserManager.updateEntity(user);
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteUser(String login) {
        return accountService.deleteUser(login);
    }

    public void checkUser(String login) {
        if (appUserManager.getWithId(login) == null) throw new ResourceNotFoundException();
    }

    public UserDetailsForm getUserData(String login) {
        AppUserDTO user = appUserManager.getWithId(login);
        return accountService.getUserDetails(user);
    }

    public boolean editUser(UserDetailsForm userDetailsForm) {
        AppUserDTO user = appUserManager.getWithId(userDetailsForm.getUsername());
        return accountService.updateUser(user, userDetailsForm);
    }
}
