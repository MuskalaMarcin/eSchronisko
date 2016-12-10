package com.eschronisko.database.service;

import com.eschronisko.database.dao.ClientDAO;
import com.eschronisko.database.dto.ClientDTO;
import org.springframework.stereotype.Service;

/**
 * Created by Marek on 08.12.2016.
 */
@Service
public class ClientManagerImpl extends ParentManagerImpl<ClientDTO, ClientDAO, Integer> implements ClientManager {
}
