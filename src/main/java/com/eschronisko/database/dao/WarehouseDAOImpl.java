package com.eschronisko.database.dao;

import com.eschronisko.database.dto.WarehouseDTO;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Marek on 08.12.2016.
 */
@Repository
public class WarehouseDAOImpl implements WarehouseDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addEntity(WarehouseDTO dto) {
        this.sessionFactory.getCurrentSession().save(dto);
    }

    @Override
    public List<WarehouseDTO> getAllEntites() {
        return this.sessionFactory.getCurrentSession().createQuery("from WarehouseDTO").list();
    }

    @Override
    public void deleteEntity(Integer id) {
        WarehouseDTO dto = sessionFactory.getCurrentSession()
                .load(WarehouseDTO.class, id);
        if (null != dto) {
            this.sessionFactory.getCurrentSession().delete(id);
        }
    }
}
