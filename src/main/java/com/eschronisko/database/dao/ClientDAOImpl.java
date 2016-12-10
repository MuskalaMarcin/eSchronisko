package com.eschronisko.database.dao;

import com.eschronisko.database.dto.ClientDTO;
import org.springframework.stereotype.Repository;

/**
 * Created by Marek on 08.12.2016.
 */
@Repository
public class ClientDAOImpl extends ParentDAOImpl<ClientDTO, Integer>  implements ClientDAO {
    public ClientDAOImpl() {
        super(ClientDTO.class);
    }
}
