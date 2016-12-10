package com.eschronisko.database.dao;

import com.eschronisko.database.dto.AppUserDTO;
import org.springframework.stereotype.Repository;

/**
 * Created by Marek on 08.12.2016.
 */
@Repository
public class AppUserDAOImpl extends ParentDAOImpl<AppUserDTO, String>  implements AppUserDAO {
    public AppUserDAOImpl() {
        super(AppUserDTO.class);
    }
}
