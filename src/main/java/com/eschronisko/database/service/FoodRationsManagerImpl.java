package com.eschronisko.database.service;

import com.eschronisko.database.dao.FoodRationsDAO;
import com.eschronisko.database.dto.FoodRationsDTO;
import org.springframework.stereotype.Service;

/**
 * Created by Marek on 08.12.2016.
 */
@Service
public class FoodRationsManagerImpl extends ParentManagerImpl<FoodRationsDTO, FoodRationsDAO, Integer> implements FoodRationsManager {
}
