package com.eschronisko.database.service;

import com.eschronisko.common.CommonService;
import com.eschronisko.common.Page;
import com.eschronisko.database.dao.ApplicationDAO;
import com.eschronisko.database.dto.ApplicationDTO;
import com.eschronisko.database.dto.ClientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Marek on 08.12.2016.
 */
@Service
public class ApplicationManagerImpl extends ParentManagerImpl<ApplicationDTO, ApplicationDAO, Integer> implements ApplicationManager {
    @Autowired
    private CommonService commonService;

    @Override
    @Transactional
    public Page<ApplicationDTO> getWaitingApplications(Integer pageNumber) {
        List<ApplicationDTO> applicationDTOList = getAllEntites().stream()
                .filter(applicationDTO -> applicationDTO.getStatus().equals("Oczekujący")).collect(Collectors.toList());
        return filterByPage(applicationDTOList, pageNumber);
    }

    @Override
    @Transactional
    public Page<ApplicationDTO> getClientsApplications(Integer pageNumber) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ClientDTO clientDTO = commonService.getLoggedUser(auth).getClient();
        List<ApplicationDTO> applicationDTOList = getAllEntites().stream().filter(applicationDTO ->
                applicationDTO.getClient().equals(clientDTO)).collect(Collectors.toList());
        return filterByPage(applicationDTOList, pageNumber);
    }

}
