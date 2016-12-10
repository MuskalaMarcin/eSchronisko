package com.eschronisko.database.dao;

import com.eschronisko.database.dto.AppUserDTO;

import java.util.List;

/**
 * Created by Marek on 08.12.2016.
 */
public interface AppUserDAO {
    void addEntity(AppUserDTO appUserDTO);

    List<AppUserDTO> getAllEntites();

    void deleteEntity(Integer appUserId);
}
