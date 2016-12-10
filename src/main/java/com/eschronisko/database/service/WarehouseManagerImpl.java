package com.eschronisko.database.service;

import com.eschronisko.database.dao.WarehouseDAO;
import com.eschronisko.database.dto.WarehouseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Marek on 08.12.2016.
 */
@Service
public class WarehouseManagerImpl implements WarehouseManager {
    @Autowired
    private WarehouseDAO dao;

    @Override
    @Transactional
    public void addEntity(WarehouseDTO dto) {
        dao.addEntity(dto);
    }

    @Override
    @Transactional
    public List<WarehouseDTO> getAllEntites() {
        return dao.getAllEntites();
    }

    @Override
    @Transactional
    public void deleteEntity(Integer id) {
        dao.deleteEntity(id);
    }

    public void setDao(WarehouseDAO dao) {
        this.dao = dao;
    }
}
