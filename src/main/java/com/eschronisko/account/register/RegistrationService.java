package com.eschronisko.account.register;

import com.eschronisko.database.service.AdministratorManager;
import com.eschronisko.database.service.AppUserManager;
import com.eschronisko.database.service.ClientManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Marcin on 10.12.2016.
 */
@Service
public class RegistrationService {
    @Autowired
    private AppUserManager appUserManager;
    @Autowired
    private AdministratorManager administratorManager;
    @Autowired
    private ClientManager clientManager;

    public boolean register(RegistrationForm registrationForm) {
        return false;
    }
}
