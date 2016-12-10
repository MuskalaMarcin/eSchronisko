package com.eschronisko.database.dao;

import com.eschronisko.database.dto.ParentDTO;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Marcin on 10.12.2016.
 */
public interface ParentDAO<A extends ParentDTO, B extends Serializable> {
    void addEntity(A dto);

    List<A> getAllEntites();

    A getWithId(B id);

    boolean deleteEntity(B id);
}
