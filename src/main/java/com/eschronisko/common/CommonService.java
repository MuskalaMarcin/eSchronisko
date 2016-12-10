package com.eschronisko.common;

import com.eschronisko.account.util.UserRole;
import com.eschronisko.database.dto.*;
import com.eschronisko.database.service.AppUserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

/**
 * Created by Marcin on 10.12.2016.
 */
@Service
public class CommonService {
    @Autowired
    private AppUserManager appUserManager;

    public void getTemplateFragments(ModelMap map) {
        getUserRoleMenu(map);
        getLoginBar(map);
    }

    private boolean isLoggedIn(Authentication auth) {
        return auth != null && auth.isAuthenticated() && auth instanceof UsernamePasswordAuthenticationToken;
    }

    public void getUserRoleMenu(ModelMap map) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        map.put("menu", "menu/anonMenu");
        if (isLoggedIn(auth)) {
            for (UserRole userRole : UserRole.values()) {
                if (auth.getAuthorities().stream().anyMatch(m -> m.toString().equals(userRole.toString()))) {
                    map.put("menu", "menu/" + userRole.toString().toLowerCase() + "Menu");
                    break;
                }
            }
        }
    }

    private String getUserName(Authentication auth) {
        String username = ((User) auth.getPrincipal()).getUsername();
        AppUserDTO user = appUserManager.getWithId(username);
        if (user.getAdministrator() != null) {
            AdministratorDTO admin = user.getAdministrator();
            return admin.getName() + " " + admin.getSurname();
        } else if (user.getAnimalKeeper() != null) {
            AnimalKeeperDTO keeper = user.getAnimalKeeper();
            return keeper.getName() + " " + keeper.getSurname();
        } else if (user.getClient() != null) {
            ClientDTO client = user.getClient();
            return client.getName() + " " + client.getSurname();
        } else if (user.getVet() != null) {
            VetDTO vet = user.getVet();
            return vet.getName() + " " + vet.getSurname();
        } else {
            return username;
        }
    }

    public void getLoginBar(ModelMap map) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (isLoggedIn(auth)) {
            map.put("loginMsg", getUserName(auth));
            map.put("loginBar", "loginBar/userInfo");
        } else {
            map.put("loginBar", "loginBar/notLogged");
        }
    }
}
