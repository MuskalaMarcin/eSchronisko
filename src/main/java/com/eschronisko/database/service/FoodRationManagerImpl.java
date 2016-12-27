package com.eschronisko.database.service;

import com.eschronisko.database.dao.FoodRationsDAO;
import com.eschronisko.database.dto.FoodRationDTO;
import org.springframework.stereotype.Service;

/**
 * Created by Marek on 08.12.2016.
 */
@Service
public class FoodRationManagerImpl extends ParentManagerImpl<FoodRationDTO, FoodRationsDAO, Integer> implements FoodRationManager {
}
