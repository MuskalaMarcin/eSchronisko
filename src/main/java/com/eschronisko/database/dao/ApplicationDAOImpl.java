package com.eschronisko.database.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eschronisko.database.dto.ApplicationDTO;

/**
 * Created by Marek on 08.12.2016.
 */
@Repository
public class ApplicationDAOImpl implements ApplicationDAO {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public void addEntity(ApplicationDTO applicationDTO) {
        this.sessionFactory.getCurrentSession().save(applicationDTO);
    }

    @Override
    public List<ApplicationDTO> getAllEntites() {
        return this.sessionFactory.getCurrentSession().createQuery("from application").list();
    }

    @Override
    public void deleteEntity(Integer applicationId) {
        ApplicationDTO animalDTO = sessionFactory.getCurrentSession()
                .load(ApplicationDTO.class, applicationId);
        if (null != animalDTO) {
            this.sessionFactory.getCurrentSession().delete(applicationId);
        }
    }
}
