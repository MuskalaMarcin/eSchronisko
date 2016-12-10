package com.eschronisko.database.service;

import com.eschronisko.database.dao.MedicalCardDAO;
import com.eschronisko.database.dto.MedicalCardDTO;
import org.springframework.stereotype.Service;

/**
 * Created by Marek on 08.12.2016.
 */
@Service
public class MedicalCardManagerImpl extends ParentManagerImpl<MedicalCardDTO, MedicalCardDAO, Integer> implements MedicalCardManager {
}
