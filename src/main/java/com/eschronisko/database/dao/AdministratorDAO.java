package com.eschronisko.database.dao;

import com.eschronisko.database.dto.AdministratorDTO;

import java.util.List;

/**
 * Created by Marek on 08.12.2016.
 */
public interface AdministratorDAO {
    public void addEntity(AdministratorDTO administratorDTO);
    public List<AdministratorDTO> getAllEntites();
    public void deleteEntity(Integer administratorId);
}
