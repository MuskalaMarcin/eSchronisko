package com.eschronisko.database.service;

import com.eschronisko.database.dto.AppUserDTO;

import java.util.List;

/**
 * Created by Marek on 08.12.2016.
 */
public interface AppUserManager {
    public void addEntity(AppUserDTO appUserDTO);

    public List<AppUserDTO> getAllEntites();

    public void deleteEntity(String appUserId);


    AppUserDTO getUserWithLogin(String login);
}
