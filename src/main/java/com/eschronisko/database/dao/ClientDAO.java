package com.eschronisko.database.dao;

import com.eschronisko.database.dto.ClientDTO;

import java.util.List;

/**
 * Created by Marek on 08.12.2016.
 */
public interface ClientDAO {
    public void addEntity(ClientDTO clientDTO);

    public List<ClientDTO> getAllEntites();

    public void deleteEntity(Integer clientId);
}
