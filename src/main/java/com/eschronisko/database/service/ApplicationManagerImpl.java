package com.eschronisko.database.service;

import com.eschronisko.database.dao.ApplicationDAO;
import com.eschronisko.database.dto.ApplicationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Marek on 08.12.2016.
 */
@Service
public class ApplicationManagerImpl implements ApplicationManager {
    @Autowired
    private ApplicationDAO dao;

    @Override
    @Transactional
    public void addEntity(ApplicationDTO dto) {
        dao.addEntity(dto);
    }

    @Override
    @Transactional
    public List<ApplicationDTO> getAllEntites() {
        return dao.getAllEntites();
    }

    @Override
    @Transactional
    public void deleteEntity(Integer id) {
        dao.deleteEntity(id);
    }

    public void setDao(ApplicationDAO dao) {
        this.dao = dao;
    }
}
