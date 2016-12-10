package com.eschronisko.database.dao;

import com.eschronisko.database.dto.DonationDTO;
import org.springframework.stereotype.Repository;

/**
 * Created by Marek on 08.12.2016.
 */
@Repository
public class DonationDAOImpl  extends ParentDAOImpl<DonationDTO, Integer>  implements DonationDAO {
    public DonationDAOImpl() {
        super(DonationDTO.class);
    }
}
