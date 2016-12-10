package com.eschronisko.common;

import com.eschronisko.account.util.UserRole;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

/**
 * Created by Marcin on 10.12.2016.
 */
@Service
public class CommonService {
    public void getTemplateFragments(ModelMap map) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        getUserRoleMenu(map, auth);
        getLoginBar(map, auth);
    }

    private boolean isLoggedIn(Authentication auth) {
        return auth != null && auth.isAuthenticated() && auth instanceof UsernamePasswordAuthenticationToken;
    }

    private void getUserRoleMenu(ModelMap map, Authentication auth) {
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
        return "testtesttest";
    }

    private void getLoginBar(ModelMap map, Authentication auth) {
        if (isLoggedIn(auth)) {
            map.put("loginMsg", getUserName(auth));
            map.put("loginBar", "loginBar/userInfo");
        } else {
            map.put("loginBar", "loginBar/notLogged");
        }
    }
}
