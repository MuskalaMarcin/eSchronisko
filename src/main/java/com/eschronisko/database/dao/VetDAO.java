package com.eschronisko.database.dao;

import com.eschronisko.database.dto.VetDTO;

import java.util.List;

/**
 * Created by Marek on 08.12.2016.
 */
public interface VetDAO {
    public void addEntity(VetDTO dto);

    public List<VetDTO> getAllEntites();

    public void deleteEntity(Integer id);
}
