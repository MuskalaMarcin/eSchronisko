package com.eschronisko.database.dao;

import com.eschronisko.database.dto.MedicalCardDTO;
import org.springframework.stereotype.Repository;

/**
 * Created by Marek on 08.12.2016.
 */
@Repository
public class MedicalCardDAOImpl extends ParentDAOImpl<MedicalCardDTO, Integer> implements MedicalCardDAO {
    public MedicalCardDAOImpl() {
        super(MedicalCardDTO.class);
    }
}
