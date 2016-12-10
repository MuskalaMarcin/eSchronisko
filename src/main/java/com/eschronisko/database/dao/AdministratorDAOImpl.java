package com.eschronisko.database.dao;

import com.eschronisko.database.dto.AdministratorDTO;
import org.springframework.stereotype.Repository;

/**
 * Created by Marek on 08.12.2016.
 */
@Repository
public class AdministratorDAOImpl extends ParentDAOImpl<AdministratorDTO, Integer> implements AdministratorDAO {
    public AdministratorDAOImpl() {
        super(AdministratorDTO.class);
    }
}
