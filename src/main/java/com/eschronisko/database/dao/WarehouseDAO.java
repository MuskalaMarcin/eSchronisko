package com.eschronisko.database.dao;

import com.eschronisko.database.dto.WarehouseDTO;

import java.util.List;

/**
 * Created by Marek on 08.12.2016.
 */
public interface WarehouseDAO {
    public void addEntity(WarehouseDTO dto);

    public List<WarehouseDTO> getAllEntites();

    public void deleteEntity(Integer id);
}
