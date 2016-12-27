package com.eschronisko.database.service;

import com.eschronisko.common.Page;
import com.eschronisko.database.dao.ParentDAO;
import com.eschronisko.database.dto.ParentDTO;
import com.eschronisko.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Marcin on 10.12.2016.
 */
public abstract class ParentManagerImpl<A extends ParentDTO, B extends ParentDAO, C extends Serializable> implements ParentManager<A, B, C> {
    private static final Integer ENTITY_PER_PAGE_NUMBER = 25;
    @Autowired
    protected B dao;

    @Override
    @Transactional
    public void addEntity(A dto) {
        dao.addEntity(dto);
    }

    @Override
    @Transactional
    public void updateEntity(A dto) {
        dao.updateEntity(dto);
    }

    @Override
    @Transactional
    public List<A> getAllEntites() {
        return dao.getAllEntites();
    }

    @Override
    @Transactional
    public Page<A> getAllEntites(Integer pageNumber) {
        return getAllEntites(pageNumber, ENTITY_PER_PAGE_NUMBER);
    }

    @Override
    @Transactional
    public Page<A> getAllEntites(Integer pageNumber, Integer entitiesPerPageNumber) {
        return filterByPage(getAllEntites(), pageNumber, entitiesPerPageNumber);
    }

    protected Page<A> filterByPage(List<A> entities, Integer pageNumber) {
        return filterByPage(entities, pageNumber, ENTITY_PER_PAGE_NUMBER);
    }

    protected Page<A> filterByPage(List<A> entities, Integer pageNumber, Integer entitiesPerPageNumber) {
        if (pageNumber == null) pageNumber = 1;
        int entitiesNumber = entities.size();
        int totalPages = (entitiesNumber / entitiesPerPageNumber) + ((entitiesNumber % entitiesPerPageNumber == 0) ? 0 : 1);
        if (pageNumber < 1 || pageNumber > totalPages) throw new ResourceNotFoundException();
        int lastIndex = ((pageNumber * entitiesPerPageNumber) > entitiesNumber) ? entitiesNumber : (pageNumber * entitiesPerPageNumber);
        return new Page<>(pageNumber, totalPages, entities.subList((pageNumber - 1) * entitiesPerPageNumber, lastIndex));
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
