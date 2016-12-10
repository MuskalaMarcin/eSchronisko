package com.eschronisko.database.service;

import com.eschronisko.database.dto.ClientDTO;

import java.util.List;

/**
 * Created by Marek on 08.12.2016.
 */
public interface ClientManager {
    public void addEntity(ClientDTO clientDTO);

    public List<ClientDTO> getAllEntites();

    public void deleteEntity(Integer clientId);
}
