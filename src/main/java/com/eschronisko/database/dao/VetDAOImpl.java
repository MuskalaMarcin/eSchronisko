package com.eschronisko.database.dao;

import com.eschronisko.database.dto.VetDTO;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Marek on 08.12.2016.
 */
@Repository
public class VetDAOImpl implements VetDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addEntity(VetDTO dto) {
        this.sessionFactory.getCurrentSession().save(dto);
    }

    @Override
    public List<VetDTO> getAllEntites() {
        return this.sessionFactory.getCurrentSession().createQuery("from VetDTO").list();
    }

    @Override
    public void deleteEntity(Integer id) {
        VetDTO dto = sessionFactory.getCurrentSession()
                .load(VetDTO.class, id);
        if (null != dto) {
            this.sessionFactory.getCurrentSession().delete(id);
        }
    }
}
