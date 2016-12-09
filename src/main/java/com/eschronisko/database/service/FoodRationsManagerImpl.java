package com.eschronisko.database.service;

import com.eschronisko.database.dao.FoodRationsDAO;
import com.eschronisko.database.dto.FoodRationsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Marek on 08.12.2016.
 */
@Service
public class FoodRationsManagerImpl implements FoodRationsManager {
    @Autowired
    private FoodRationsDAO dao;
    @Override
    @Transactional
    public void addEntity(FoodRationsDTO dto) {
        dao.addEntity(dto);
    }
    @Override
    @Transactional
    public List<FoodRationsDTO> getAllEntites() {
        return dao.getAllEntites();
    }
    @Override
    @Transactional
    public void deleteEntity(Integer id) {
        dao.deleteEntity(id);
    }
    public void setDao(FoodRationsDAO dao) {
        this.dao = dao;
    }
}
