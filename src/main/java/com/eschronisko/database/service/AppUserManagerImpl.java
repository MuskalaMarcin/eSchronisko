package com.eschronisko.database.service;

import com.eschronisko.database.dao.AppUserDAO;
import com.eschronisko.database.dto.AppUserDTO;
import org.springframework.stereotype.Service;

/**
 * Created by Marek on 08.12.2016.
 */
@Service
public class AppUserManagerImpl extends ParentManagerImpl<AppUserDTO, AppUserDAO, String> implements AppUserManager {
}
