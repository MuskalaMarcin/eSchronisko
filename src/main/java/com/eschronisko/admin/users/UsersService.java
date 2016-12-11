package com.eschronisko.admin.users;

import com.eschronisko.account.AccountService;
import com.eschronisko.account.util.UserDetailsForm;
import com.eschronisko.database.service.AppUserManager;
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

    public List<UserDetailsForm> getActiveUsersDetails() {
        return appUserManager.getActiveUsers().stream().map(accountService::getUserDetails).collect(Collectors.toList());
    }

    public List<UserDetailsForm> getNotActiveUsersDetails() {
        return appUserManager.getNotActiveUsers().stream().map(accountService::getUserDetails).collect(Collectors.toList());
    }

    public List<UserDetailsForm> getUsersStartingWith(final String prefix) {
        return getAllUsersDetails().stream().filter(f -> StringUtils.startsWithIgnoreCase(f.getName(), prefix)
                || StringUtils.startsWithIgnoreCase(f.getSurname(), prefix)
                || StringUtils.startsWithIgnoreCase(f.getUsername(), prefix)
        ).collect(Collectors.toList());
    }

    private List<UserDetailsForm> getAllUsersDetails() {
        return appUserManager.getAllEntites().stream().map(accountService::getUserDetails).collect(Collectors.toList());
    }
}
