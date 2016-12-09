package com.eschronisko.database.service;

import com.eschronisko.database.dao.MedicalTreatmentDAO;
import com.eschronisko.database.dto.MedicalTreatmentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Marek on 08.12.2016.
 */
@Service
public class MedicalTreatmentManagerImpl implements MedicalTreatmentManager {
    @Autowired
    private MedicalTreatmentDAO dao;
    @Override
    @Transactional
    public void addEntity(MedicalTreatmentDTO dto) {
        dao.addEntity(dto);
    }
    @Override
    @Transactional
    public List<MedicalTreatmentDTO> getAllEntites() {
        return dao.getAllEntites();
    }
    @Override
    @Transactional
    public void deleteEntity(Integer id) {
        dao.deleteEntity(id);
    }
    public void setDao(MedicalTreatmentDAO dao) {
        this.dao = dao;
    }
}
