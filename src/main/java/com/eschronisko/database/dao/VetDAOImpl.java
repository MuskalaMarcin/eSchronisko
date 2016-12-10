package com.eschronisko.database.dao;

import com.eschronisko.database.dto.VetDTO;
import org.springframework.stereotype.Repository;

/**
 * Created by Marek on 08.12.2016.
 */
@Repository
public class VetDAOImpl extends ParentDAOImpl<VetDTO, Integer>  implements VetDAO {
    public VetDAOImpl() {
        super(VetDTO.class);
    }
}
