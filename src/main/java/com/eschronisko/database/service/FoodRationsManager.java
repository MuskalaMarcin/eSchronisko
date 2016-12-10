package com.eschronisko.database.service;

import com.eschronisko.database.dto.FoodRationsDTO;

import java.util.List;

/**
 * Created by Marek on 08.12.2016.
 */
public interface FoodRationsManager {
    public void addEntity(FoodRationsDTO foodRationsDTO);

    public List<FoodRationsDTO> getAllEntites();

    public void deleteEntity(Integer foodRationsId);
}
