package com.eschronisko.database.dao;

import com.eschronisko.database.dto.AnimalDTO;

import java.util.List;

/**
 * Created by Marek on 08.12.2016.
 */
public interface AnimalDAO {
    public void addEntity(AnimalDTO animalDTO);
    public List<AnimalDTO> getAllEntites();
    public void deleteEntity(Integer animalId);
}
