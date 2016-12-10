package com.eschronisko.database.service;

import com.eschronisko.database.dao.AnimalDAO;
import com.eschronisko.database.dto.AnimalDTO;
import org.springframework.stereotype.Service;

/**
 * Created by Marek on 08.12.2016.
 */
@Service
public class AnimalManagerImpl extends ParentManagerImpl<AnimalDTO, AnimalDAO, Integer> implements AnimalManager {
}
