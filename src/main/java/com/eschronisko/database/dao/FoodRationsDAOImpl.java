package com.eschronisko.database.dao;

import com.eschronisko.database.dto.FoodRationDTO;
import org.springframework.stereotype.Repository;

/**
 * Created by Marek on 08.12.2016.
 */
@Repository
public class FoodRationsDAOImpl extends ParentDAOImpl<FoodRationDTO, Integer> implements FoodRationsDAO {
    public FoodRationsDAOImpl() {
        super(FoodRationDTO.class);
    }
}
