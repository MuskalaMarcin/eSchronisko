package com.eschronisko.database.service;

import com.eschronisko.database.dao.ClientDAO;
import com.eschronisko.database.dto.ClientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Marek on 08.12.2016.
 */
@Service
public class ClientManagerImpl implements ClientManager {
    @Autowired
    private ClientDAO dao;
    @Override
    @Transactional
    public void addEntity(ClientDTO dto) {
        dao.addEntity(dto);
    }
    @Override
    @Transactional
    public List<ClientDTO> getAllEntites() {
        return dao.getAllEntites();
    }
    @Override
    @Transactional
    public void deleteEntity(Integer id) {
        dao.deleteEntity(id);
    }
    public void setDao(ClientDAO dao) {
        this.dao = dao;
    }
}
