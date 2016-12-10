package com.eschronisko.database.service;

import com.eschronisko.database.dao.ClientDAO;
import com.eschronisko.database.dto.ClientDTO;

/**
 * Created by Marek on 08.12.2016.
 */
public interface ClientManager extends ParentManager<ClientDTO, ClientDAO, Integer> {
}
