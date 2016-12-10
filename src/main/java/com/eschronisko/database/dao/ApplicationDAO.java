package com.eschronisko.database.dao;

import com.eschronisko.database.dto.ApplicationDTO;

import java.util.List;

/**
 * Created by Marek on 08.12.2016.
 */
public interface ApplicationDAO {
    public void addEntity(ApplicationDTO applicationDTO);

    public List<ApplicationDTO> getAllEntites();

    public void deleteEntity(Integer applicationId);
}
