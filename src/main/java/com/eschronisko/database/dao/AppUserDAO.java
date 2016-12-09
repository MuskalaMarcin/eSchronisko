package com.eschronisko.database.dao;

import com.eschronisko.database.dto.AppUserDTO;

import java.util.List;

/**
 * Created by Marek on 08.12.2016.
 */
public interface AppUserDAO {
    public void addEntity(AppUserDTO appUserDTO);
    public List<AppUserDTO> getAllEntites();
    public void deleteEntity(Integer appUserId);
}
