package com.eschronisko.database.service;

import com.eschronisko.common.Page;
import com.eschronisko.database.dao.AppUserDAO;
import com.eschronisko.database.dto.AppUserDTO;

import java.util.List;

/**
 * Created by Marek on 08.12.2016.
 */
public interface AppUserManager extends ParentManager<AppUserDTO, AppUserDAO, String> {
    Page<AppUserDTO> getActiveUsers(Integer pageNumber);

    Page<AppUserDTO> getNotActiveUsers(Integer pageNumber);
}
