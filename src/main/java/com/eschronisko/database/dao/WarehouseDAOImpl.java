package com.eschronisko.database.dao;

import com.eschronisko.database.dto.WarehouseDTO;
import org.springframework.stereotype.Repository;

/**
 * Created by Marek on 08.12.2016.
 */
@Repository
public class WarehouseDAOImpl extends ParentDAOImpl<WarehouseDTO, Integer> implements WarehouseDAO {
    public WarehouseDAOImpl() {
        super(WarehouseDTO.class);
    }
}
