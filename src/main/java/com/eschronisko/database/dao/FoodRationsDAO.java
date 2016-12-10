package com.eschronisko.database.dao;

import com.eschronisko.database.dto.FoodRationsDTO;

import java.util.List;

/**
 * Created by Marek on 08.12.2016.
 */
public interface FoodRationsDAO {
    public void addEntity(FoodRationsDTO foodRationsDTO);

    public List<FoodRationsDTO> getAllEntites();

    public void deleteEntity(Integer foodRationsId);
}
