package com.eschronisko.database.dao;

import com.eschronisko.database.dto.FoodRationsDTO;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Marek on 08.12.2016.
 */
@Repository
public class FoodRationsDAOImpl implements FoodRationsDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addEntity(FoodRationsDTO dto) {
        this.sessionFactory.getCurrentSession().save(dto);
    }

    @Override
    public List<FoodRationsDTO> getAllEntites() {
        return this.sessionFactory.getCurrentSession().createQuery("from FoodRationsDTO").list();
    }

    @Override
    public void deleteEntity(Integer id) {
        FoodRationsDTO dto = sessionFactory.getCurrentSession()
                .load(FoodRationsDTO.class, id);
        if (null != dto) {
            this.sessionFactory.getCurrentSession().delete(id);
        }
    }
}
