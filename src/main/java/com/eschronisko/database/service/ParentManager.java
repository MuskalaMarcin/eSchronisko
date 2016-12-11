package com.eschronisko.database.service;

import com.eschronisko.database.dao.ParentDAO;
import com.eschronisko.database.dto.ParentDTO;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Marcin on 10.12.2016.
 */
public interface ParentManager<A extends ParentDTO, B extends ParentDAO, C extends Serializable> {
    void addEntity(A dto);

    void updateEntity(A dto);

    List<A> getAllEntites();

    void deleteEntity(C id);

    A getWithId(C id);
}
