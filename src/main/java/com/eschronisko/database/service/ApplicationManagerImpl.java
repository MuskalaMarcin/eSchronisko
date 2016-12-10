package com.eschronisko.database.service;

import com.eschronisko.database.dao.ApplicationDAO;
import com.eschronisko.database.dto.ApplicationDTO;
import org.springframework.stereotype.Service;

/**
 * Created by Marek on 08.12.2016.
 */
@Service
public class ApplicationManagerImpl extends ParentManagerImpl<ApplicationDTO, ApplicationDAO, Integer> implements ApplicationManager {
}
