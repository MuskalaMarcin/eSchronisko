package com.eschronisko.database.dao;

import com.eschronisko.database.dto.FoodRationsDTO;
import org.springframework.stereotype.Repository;

/**
 * Created by Marek on 08.12.2016.
 */
@Repository
public class FoodRationsDAOImpl extends ParentDAOImpl<FoodRationsDTO, Integer> implements FoodRationsDAO {
    public FoodRationsDAOImpl() {
        super(FoodRationsDTO.class);
    }
}
