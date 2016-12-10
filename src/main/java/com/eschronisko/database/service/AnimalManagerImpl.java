package com.eschronisko.database.service;

import com.eschronisko.database.dao.AnimalDAO;
import com.eschronisko.database.dto.AnimalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Marek on 08.12.2016.
 */
@Service
public class AnimalManagerImpl implements AnimalManager {
    @Autowired
    private AnimalDAO dao;

    @Override
    @Transactional
    public void addEntity(AnimalDTO dto) {
        dao.addEntity(dto);
    }

    @Override
    @Transactional
    public List<AnimalDTO> getAllEntites() {
        return dao.getAllEntites();
    }

    @Override
    @Transactional
    public void deleteEntity(Integer id) {
        dao.deleteEntity(id);
    }

    public void setDao(AnimalDAO dao) {
        this.dao = dao;
    }
}
