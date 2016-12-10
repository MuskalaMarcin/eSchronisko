package com.eschronisko.database.service;

import com.eschronisko.database.dao.MedicalTreatmentDAO;
import com.eschronisko.database.dto.MedicalTreatmentDTO;
import org.springframework.stereotype.Service;

/**
 * Created by Marek on 08.12.2016.
 */
@Service
public class MedicalTreatmentManagerImpl extends ParentManagerImpl<MedicalTreatmentDTO, MedicalTreatmentDAO, Integer> implements MedicalTreatmentManager {
}
