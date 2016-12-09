package com.eschronisko.database.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eschronisko.database.dto.ClientDTO;

/**
 * Created by Marek on 08.12.2016.
 */
@Repository
public class ClientDAOImpl implements ClientDAO {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public void addEntity(ClientDTO clientDTO) {
        this.sessionFactory.getCurrentSession().save(clientDTO);
    }

    @Override
    public List<ClientDTO> getAllEntites() {
        return this.sessionFactory.getCurrentSession().createQuery("from client").list();
    }

    @Override
    public void deleteEntity(Integer clientId) {
        ClientDTO animalDTO = sessionFactory.getCurrentSession()
                .load(ClientDTO.class, clientId);
        if (null != animalDTO) {
            this.sessionFactory.getCurrentSession().delete(clientId);
        }
    }
}
