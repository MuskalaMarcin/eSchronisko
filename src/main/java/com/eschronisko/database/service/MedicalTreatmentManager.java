package com.eschronisko.database.service;

import com.eschronisko.database.dto.MedicalTreatmentDTO;

import java.util.List;

/**
 * Created by Marek on 08.12.2016.
 */
public interface MedicalTreatmentManager {
    public void addEntity(MedicalTreatmentDTO dto);

    public List<MedicalTreatmentDTO> getAllEntites();

    public void deleteEntity(Integer id);
}
