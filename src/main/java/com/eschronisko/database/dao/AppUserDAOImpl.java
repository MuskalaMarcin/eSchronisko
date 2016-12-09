package com.eschronisko.database.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.eschronisko.database.dto.AppUserDTO;
import org.springframework.stereotype.Repository;

/**
 * Created by Marek on 08.12.2016.
 */
@Repository
public class AppUserDAOImpl implements AppUserDAO {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public void addEntity(AppUserDTO dto) {
        this.sessionFactory.getCurrentSession().save(dto);
    }

    @Override
    public List<AppUserDTO> getAllEntites() {
        return this.sessionFactory.getCurrentSession().createQuery("from app_user").list();
    }

    @Override
    public void deleteEntity(Integer id) {
        AppUserDTO dto = sessionFactory.getCurrentSession()
                .load(AppUserDTO.class, id);
        if (null != dto) {
            this.sessionFactory.getCurrentSession().delete(id);
        }
    }
}
