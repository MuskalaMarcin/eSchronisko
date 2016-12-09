package com.eschronisko.database.dao;

import com.eschronisko.database.dto.AnimalKeeperDTO;

import java.util.List;

/**
 * Created by Marek on 08.12.2016.
 */
public interface AnimalKeeperDAO {
    public void addEntity(AnimalKeeperDTO animalKeeperDTO);
    public List<AnimalKeeperDTO> getAllEntites();
    public void deleteEntity(Integer animalKeeperId);
}
