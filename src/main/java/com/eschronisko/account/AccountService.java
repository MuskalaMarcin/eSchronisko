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
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public UserDetailsForm getUserDetails(AppUserDTO appUserDTO) {
        UserDetailsForm user = new UserDetailsForm();

        user.setUserRole(appUserDTO.getUserRole());
        user.seteMail(appUserDTO.geteMail());
        user.setPassword("");
        user.setPasswordRepeat("");
        user.setUsername(appUserDTO.getLogin());
        user.setIsActive(appUserDTO.getIsActive() == 1);

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


    public UserDetailsForm getCurrentValues(Authentication auth) {
        AppUserDTO appUserDTO = commonService.getLoggedUser(auth);
        return getUserDetails(appUserDTO);
    }


    private void encodePasswords() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("D:\\Projekty\\eSchronisko\\database\\inserts\\appUser.sql"));
            Pattern pattern = Pattern.compile("(insert into app_user \\(login, password, e_mail, is_active, user_role, client_id, vet_id, animal_keeper_id, administrator_id\\) values \\('\\w+', )('.+')((, '.+')+.*)");
            LinkedList<String> output = new LinkedList<>();
            lines.forEach(l -> {
                System.out.println(l);
                Matcher m = pattern.matcher(l);
                System.out.println("matches: czy nie maczes " + m.matches());
                String out = m.group(1) + "'" + passwordEncoder.encode(m.group(2).replaceAll("'", "")) + "' /*" + m.group(2).replaceAll("'", "") + "*/ " + m.group(3);
                output.add(out);
                System.out.println(out);
            });
            Files.write(Paths.get("D:\\Projekty\\eSchronisko\\database\\inserts\\appUserConv.sql"), output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean register(UserDetailsForm userDetailsForm) {
        UserRole userRole = UserRole.getUserRole(userDetailsForm.getUserRole());
        encodePasswords();
        try {
            AppUserDTO appUserDTO = new AppUserDTO();
            appUserDTO.seteMail(userDetailsForm.geteMail());
            appUserDTO.setIsActive(0);
            appUserDTO.setLogin(userDetailsForm.getUsername());
            appUserDTO.setPassword(passwordEncoder.encode(userDetailsForm.getPassword()));
            appUserDTO.setUserRole(userRole.getRoleValue());

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
        AppUserDTO appUserDTO = commonService.getLoggedUser(auth);
        return updateUser(appUserDTO, userDetailsForm);
    }

    public boolean updateUser(AppUserDTO appUserDTO, UserDetailsForm userDetailsForm) {
        try {
            UserRole userRole = UserRole.getUserRole(userDetailsForm.getUserRole());
            appUserDTO.seteMail(userDetailsForm.geteMail());
            appUserDTO.setLogin(userDetailsForm.getUsername());
            if (!StringUtils.isEmpty(userDetailsForm.getPassword())) {
                appUserDTO.setPassword(passwordEncoder.encode(userDetailsForm.getPassword()));
            }

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

    public boolean deleteUser(String login) {
        try {
            AppUserDTO user = appUserManager.getWithId(login);
            UserRole role = UserRole.getUserRole(user.getUserRole());
            int roleAccountId = -1;
            switch (role) {
                case ADMIN:
                    roleAccountId = user.getAdministrator().getId();
                    break;
                case VET:
                    roleAccountId = user.getVet().getId();
                    break;
                case KEEPER:
                    roleAccountId = user.getAnimalKeeper().getId();
                    break;
                case CLIENT:
                    roleAccountId = user.getClient().getId();
                    break;
            }
            appUserManager.deleteEntity(login);
            switch (role) {
                case ADMIN:
                    administratorManager.deleteEntity(roleAccountId);
                    break;
                case VET:
                    vetManager.deleteEntity(roleAccountId);
                    break;
                case KEEPER:
                    animalKeeperManager.deleteEntity(roleAccountId);
                    break;
                case CLIENT:
                    clientManager.deleteEntity(roleAccountId);
                    break;
            }
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }
}
