package com.eschronisko.database.service;

import com.eschronisko.database.dao.AdministratorDAO;
import com.eschronisko.database.dto.AdministratorDTO;
import org.springframework.stereotype.Service;

/**
 * Created by Marek on 08.12.2016.
 */
@Service
public class AdministratorManagerImpl extends ParentManagerImpl<AdministratorDTO, AdministratorDAO, Integer> implements AdministratorManager {
}
