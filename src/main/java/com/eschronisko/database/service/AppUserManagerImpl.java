package com.eschronisko.database.service;

import com.eschronisko.database.dao.AppUserDAO;
import com.eschronisko.database.dto.AppUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Marek on 08.12.2016.
 */
@Service
public class AppUserManagerImpl implements AppUserManager {
    @Autowired
    private AppUserDAO dao;
    @Override
    @Transactional
    public void addEntity(AppUserDTO dto) {
        dao.addEntity(dto);
    }
    @Override
    @Transactional
    public List<AppUserDTO> getAllEntites() {
        return dao.getAllEntites();
    }
    @Override
    @Transactional
    public void deleteEntity(Integer id) {
        dao.deleteEntity(id);
    }
    public void setDao(AppUserDAO dao) {
        this.dao = dao;
    }
}