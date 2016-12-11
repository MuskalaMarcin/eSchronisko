package com.eschronisko.database.service;

import com.eschronisko.database.dao.AnimalKeeperDAO;
import com.eschronisko.database.dto.AnimalKeeperDTO;
import org.springframework.stereotype.Service;

/**
 * Created by Marcin on 10.12.2016.
 */
@Service
public class AnimalKeeperManagerImpl extends ParentManagerImpl<AnimalKeeperDTO, AnimalKeeperDAO, Integer> implements AnimalKeeperManager {
}
