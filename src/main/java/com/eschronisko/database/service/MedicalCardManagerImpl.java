package com.eschronisko.database.service;

import com.eschronisko.database.dao.MedicalCardDAO;
import com.eschronisko.database.dto.MedicalCardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Marek on 08.12.2016.
 */
@Service
public class MedicalCardManagerImpl implements MedicalCardManager {
    @Autowired
    private MedicalCardDAO dao;
    @Override
    @Transactional
    public void addEntity(MedicalCardDTO dto) {
        dao.addEntity(dto);
    }
    @Override
    @Transactional
    public List<MedicalCardDTO> getAllEntites() {
        return dao.getAllEntites();
    }
    @Override
    @Transactional
    public void deleteEntity(Integer id) {
        dao.deleteEntity(id);
    }
    public void setDao(MedicalCardDAO dao) {
        this.dao = dao;
    }
}
