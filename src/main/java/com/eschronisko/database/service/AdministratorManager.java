package com.eschronisko.database.service;

import com.eschronisko.database.dto.AdministratorDTO;

import java.util.List;

/**
 * Created by Marek on 08.12.2016.
 */
public interface AdministratorManager {
    public void addEntity(AdministratorDTO dto);

    public List<AdministratorDTO> getAllEntites();

    public void deleteEntity(Integer id);
}
