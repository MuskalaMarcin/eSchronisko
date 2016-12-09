package com.eschronisko.database.service;

import com.eschronisko.database.dto.AdministratorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eschronisko.database.dao.AdministratorDAO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Marek on 08.12.2016.
 */
@Service
public class AdministratorManagerImpl implements AdministratorManager {
    @Autowired
    private AdministratorDAO dao;
    @Override
    @Transactional
    public void addEntity(AdministratorDTO dto) {
        dao.addEntity(dto);
    }
    @Override
    @Transactional
    public List<AdministratorDTO> getAllEntites() {
        return dao.getAllEntites();
    }
    @Override
    @Transactional
    public void deleteEntity(Integer administratorId) {
        dao.deleteEntity(administratorId);
    }
    public void setDao(AdministratorDAO dao) {
        this.dao = dao;
    }
}
