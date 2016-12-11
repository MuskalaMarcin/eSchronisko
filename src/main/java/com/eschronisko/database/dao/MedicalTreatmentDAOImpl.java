package com.eschronisko.database.dao;

import com.eschronisko.database.dto.MedicalTreatmentDTO;
import org.springframework.stereotype.Repository;

/**
 * Created by Marek on 09.12.2016.
 */
@Repository
public class MedicalTreatmentDAOImpl extends ParentDAOImpl<MedicalTreatmentDTO, Integer> implements MedicalTreatmentDAO {
    public MedicalTreatmentDAOImpl() {
        super(MedicalTreatmentDTO.class);
    }
}
