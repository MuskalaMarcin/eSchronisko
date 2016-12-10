package com.eschronisko.database.service;

import com.eschronisko.database.dao.WarehouseDAO;
import com.eschronisko.database.dto.WarehouseDTO;
import org.springframework.stereotype.Service;

/**
 * Created by Marek on 08.12.2016.
 */
@Service
public class WarehouseManagerImpl extends ParentManagerImpl<WarehouseDTO, WarehouseDAO, Integer> implements WarehouseManager {
}
