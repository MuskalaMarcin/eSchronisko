package com.eschronisko.database.dao;

import com.eschronisko.database.dto.ParentDTO;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Marcin on 10.12.2016.
 */
abstract class ParentDAOImpl<A extends ParentDTO, B extends Serializable> implements ParentDAO<A, B> {
    @Autowired
    protected SessionFactory sessionFactory;

    protected final Class<A> clazz;
    protected final String clazzName;

    protected ParentDAOImpl(Class<A> clazz) {
        this.clazz = clazz;
        this.clazzName = clazz.toString().replaceAll(".*\\.", "");
    }

    @Override
    public void addEntity(A dto) {
        this.sessionFactory.getCurrentSession().save(dto);
    }

    @Override
    public void updateEntity(A dto) {
        this.sessionFactory.getCurrentSession().update(dto);
    }

    @Override
    public List<A> getAllEntites() {
        return this.sessionFactory.getCurrentSession().createQuery("from " + clazzName).list();
    }

    @Override
    public A getWithId(B id) {
        return this.sessionFactory.getCurrentSession().get(clazz, id);
    }

    @Override
    public boolean deleteEntity(B id) {
        A dto = getWithId(id);
        if (null != dto) {
            this.sessionFactory.getCurrentSession().delete(id);
            return true;
        } else {
            return false;
        }
    }
}
