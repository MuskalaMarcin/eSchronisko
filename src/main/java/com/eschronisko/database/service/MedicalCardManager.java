package com.eschronisko.database.service;

import com.eschronisko.database.dto.MedicalCardDTO;

import java.util.List;

/**
 * Created by Marek on 08.12.2016.
 */
public interface MedicalCardManager {
    public void addEntity(MedicalCardDTO dto);

    public List<MedicalCardDTO> getAllEntites();

    public void deleteEntity(Integer id);
}
