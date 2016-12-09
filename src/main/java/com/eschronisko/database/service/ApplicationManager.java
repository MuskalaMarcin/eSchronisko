package com.eschronisko.database.service;

import com.eschronisko.database.dto.ApplicationDTO;

import java.util.List;

/**
 * Created by Marek on 08.12.2016.
 */
public interface ApplicationManager {
    public void addEntity(ApplicationDTO dto);
    public List<ApplicationDTO> getAllEntites();
    public void deleteEntity(Integer id);
}
