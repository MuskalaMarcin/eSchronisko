package com.eschronisko.account;

import com.eschronisko.database.dto.AppUserDTO;
import com.eschronisko.database.service.AppUserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Marcin on 10.12.2016.
 */

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private AppUserManager appUserManager;

    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        AppUserDTO user = appUserManager.getUserWithLogin(username);
        List<GrantedAuthority> authorityList = buildUserAuthority(user.getUserRole());

        return buildUserForAuthentication(user, authorityList);
    }

    private User buildUserForAuthentication(AppUserDTO user, List<GrantedAuthority> authorities) {
        return new User(user.getLogin(), user.getPassword(),
                user.getIsActive() != 0, true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(String userRole) {
        return Stream.of(userRole).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
}
