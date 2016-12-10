package com.eschronisko.database.dao;

import com.eschronisko.database.dto.ApplicationDTO;
import org.springframework.stereotype.Repository;

/**
 * Created by Marek on 08.12.2016.
 */
@Repository
public class ApplicationDAOImpl extends ParentDAOImpl<ApplicationDTO, Integer> implements ApplicationDAO {
    public ApplicationDAOImpl() {
        super(ApplicationDTO.class);
    }
}
