package com.eschronisko.database.service;

import com.eschronisko.common.Page;
import com.eschronisko.database.dao.AppUserDAO;
import com.eschronisko.database.dto.AppUserDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Marek on 08.12.2016.
 */
@Service
public class AppUserManagerImpl extends ParentManagerImpl<AppUserDTO, AppUserDAO, String> implements AppUserManager {
    @Override
    @Transactional
    public Page<AppUserDTO> getActiveUsers(Integer pageNumber) {
        List<AppUserDTO> activeUsers = getAllEntites().stream().filter(f -> f.getIsActive() == 1).collect(Collectors.toList());
        return filterByPage(activeUsers, pageNumber);
    }

    @Override
    @Transactional
    public Page<AppUserDTO> getNotActiveUsers(Integer pageNumber) {
        List<AppUserDTO> notActiveUsers = getAllEntites().stream().filter(f -> f.getIsActive() == 0).collect(Collectors.toList());
        return filterByPage(notActiveUsers, pageNumber);
    }
}
