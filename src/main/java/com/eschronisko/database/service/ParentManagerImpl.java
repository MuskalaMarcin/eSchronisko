package com.eschronisko.database.service;

import com.eschronisko.database.dao.ParentDAO;
import com.eschronisko.database.dto.ParentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Marcin on 10.12.2016.
 */
public abstract class ParentManagerImpl<A extends ParentDTO, B extends ParentDAO, C extends Serializable> implements ParentManager<A, B, C> {
    @Autowired
    protected B dao;

    @Override
    @Transactional
    public void addEntity(A dto) {
        dao.addEntity(dto);
    }

    @Override
    @Transactional
    public List<A> getAllEntites() {
        return dao.getAllEntites();
    }

    @Override
    @Transactional
    public void deleteEntity(C id) {
        dao.deleteEntity(id);
    }


    @Override
    @Transactional
    public A getWithId(C login) {
        return (A) dao.getWithId(login);
    }
}
