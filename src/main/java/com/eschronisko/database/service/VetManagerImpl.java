package com.eschronisko.database.service;

import com.eschronisko.database.dao.VetDAO;
import com.eschronisko.database.dto.VetDTO;
import org.springframework.stereotype.Service;

/**
 * Created by Marek on 08.12.2016.
 */
@Service
public class VetManagerImpl extends ParentManagerImpl<VetDTO, VetDAO, Integer> implements VetManager {
}
