package com.eschronisko.database.service;

import com.eschronisko.database.dao.ApplicationDAO;
import com.eschronisko.database.dto.ApplicationDTO;

/**
 * Created by Marek on 08.12.2016.
 */
public interface ApplicationManager extends ParentManager<ApplicationDTO, ApplicationDAO, Integer> {
}
