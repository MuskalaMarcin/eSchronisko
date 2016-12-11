package com.eschronisko.account;

import com.eschronisko.account.util.UserDetailsForm;
import com.eschronisko.account.util.UserRole;
import com.eschronisko.common.CommonService;
import com.eschronisko.database.dto.*;
import com.eschronisko.database.service.*;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by Marcin on 10.12.2016.
 */
@Service
public class AccountService {
    @Autowired
    private AppUserManager appUserManager;
    @Autowired
    private AdministratorManager administratorManager;
    @Autowired
    private ClientManager clientManager;
    @Autowired
    private VetManager vetManager;
    @Autowired
    private AnimalKeeperManager animalKeeperManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CommonService commonService;


    public UserDetailsForm getCurrentValues(Authentication auth) {
        AppUserDTO appUserDTO = commonService.getLoggedUser(auth);
        UserDetailsForm user = new UserDetailsForm();

        user.setUserRole(appUserDTO.getUserRole());
        user.seteMail(appUserDTO.geteMail());
        user.setPassword("");
        user.setPasswordRepeat("");
        user.setUsername(appUserDTO.getLogin());

        switch (UserRole.getUserRole(appUserDTO.getUserRole())) {
            case VET:
                VetDTO vetDTO = appUserDTO.getVet();
                user.setAddress(vetDTO.getAddress());
                user.setName(vetDTO.getName());
                user.setSurname(vetDTO.getSurname());
                break;
            case KEEPER:
                AnimalKeeperDTO animalKeeperDTO = appUserDTO.getAnimalKeeper();
                user.setAddress(animalKeeperDTO.getAddress());
                user.setName(animalKeeperDTO.getName());
                user.setSurname(animalKeeperDTO.getSurname());
                break;
            case CLIENT:
                ClientDTO clientDTO = appUserDTO.getClient();
                user.setAddress(clientDTO.getAddress());
                user.setName(clientDTO.getName());
                user.setSurname(clientDTO.getSurname());
                break;
            case ADMIN:
                AdministratorDTO administratorDTO = appUserDTO.getAdministrator();
                user.setAddress(administratorDTO.getAddress());
                user.setName(administratorDTO.getName());
                user.setSurname(administratorDTO.getSurname());
                break;
        }

        return user;
    }

    public boolean register(UserDetailsForm userDetailsForm) {
        UserRole userRole = UserRole.getUserRole(userDetailsForm.getUserRole());

        try {
            AppUserDTO appUserDTO = new AppUserDTO();
            appUserDTO.seteMail(userDetailsForm.geteMail());
            appUserDTO.setIsActive(0);
            appUserDTO.setLogin(userDetailsForm.getUsername());
            appUserDTO.setPassword(passwordEncoder.encode(userDetailsForm.getPassword()));
            appUserDTO.setUserRole(userRole.toString());

            switch (userRole) {
                case ADMIN:
                    AdministratorDTO administratorDTO = new AdministratorDTO();
                    administratorDTO.setAddress(userDetailsForm.getAddress());
                    administratorDTO.setName(userDetailsForm.getName());
                    administratorDTO.setSurname(userDetailsForm.getSurname());
                    appUserDTO.setAdministrator(administratorDTO);
                    administratorDTO.setAppUser(appUserDTO);

                    administratorManager.addEntity(administratorDTO);
                    break;
                case CLIENT:
                    ClientDTO clientDTO = new ClientDTO();
                    clientDTO.setAddress(userDetailsForm.getAddress());
                    clientDTO.setName(userDetailsForm.getName());
                    clientDTO.setSurname(userDetailsForm.getSurname());
                    appUserDTO.setClient(clientDTO);
                    clientDTO.setAppUser(appUserDTO);

                    clientManager.addEntity(clientDTO);
                    break;
                case KEEPER:
                    AnimalKeeperDTO animalKeeperDTO = new AnimalKeeperDTO();
                    animalKeeperDTO.setAddress(userDetailsForm.getAddress());
                    animalKeeperDTO.setName(userDetailsForm.getName());
                    animalKeeperDTO.setSurname(userDetailsForm.getSurname());
                    appUserDTO.setAnimalKeeper(animalKeeperDTO);
                    animalKeeperDTO.setAppUser(appUserDTO);

                    animalKeeperManager.addEntity(animalKeeperDTO);
                    break;
                case VET:
                    VetDTO vetDTO = new VetDTO();
                    vetDTO.setAddress(userDetailsForm.getAddress());
                    vetDTO.setName(userDetailsForm.getName());
                    vetDTO.setSurname(userDetailsForm.getSurname());
                    appUserDTO.setVet(vetDTO);
                    vetDTO.setAppUser(appUserDTO);

                    vetManager.addEntity(vetDTO);
                    break;
            }

            appUserManager.addEntity(appUserDTO);
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateSettings(UserDetailsForm userDetailsForm, Authentication auth) {
        UserRole userRole = UserRole.getUserRole(userDetailsForm.getUserRole());

        try {
            AppUserDTO appUserDTO = commonService.getLoggedUser(auth);
            appUserDTO.seteMail(userDetailsForm.geteMail());
            appUserDTO.setLogin(userDetailsForm.getUsername());
            appUserDTO.setPassword(passwordEncoder.encode(userDetailsForm.getPassword()));

            switch (userRole) {
                case ADMIN:
                    AdministratorDTO administratorDTO = appUserDTO.getAdministrator();
                    administratorDTO.setAddress(userDetailsForm.getAddress());
                    administratorDTO.setName(userDetailsForm.getName());
                    administratorDTO.setSurname(userDetailsForm.getSurname());

                    administratorManager.updateEntity(administratorDTO);
                    break;
                case CLIENT:
                    ClientDTO clientDTO = appUserDTO.getClient();
                    clientDTO.setAddress(userDetailsForm.getAddress());
                    clientDTO.setName(userDetailsForm.getName());
                    clientDTO.setSurname(userDetailsForm.getSurname());

                    clientManager.updateEntity(clientDTO);
                    break;
                case KEEPER:
                    AnimalKeeperDTO animalKeeperDTO = appUserDTO.getAnimalKeeper();
                    animalKeeperDTO.setAddress(userDetailsForm.getAddress());
                    animalKeeperDTO.setName(userDetailsForm.getName());
                    animalKeeperDTO.setSurname(userDetailsForm.getSurname());

                    animalKeeperManager.updateEntity(animalKeeperDTO);
                    break;
                case VET:
                    VetDTO vetDTO = appUserDTO.getVet();
                    vetDTO.setAddress(userDetailsForm.getAddress());
                    vetDTO.setName(userDetailsForm.getName());
                    vetDTO.setSurname(userDetailsForm.getSurname());

                    vetManager.updateEntity(vetDTO);
                    break;
            }

            appUserManager.updateEntity(appUserDTO);
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }
}
