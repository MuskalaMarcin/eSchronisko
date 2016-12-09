package com.eschronisko.database.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.eschronisko.database.dto.MedicalCardDTO;
import org.springframework.stereotype.Repository;

/**
 * Created by Marek on 08.12.2016.
 */
@Repository
public class MedicalCardDAOImpl implements MedicalCardDAO {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public void addEntity(MedicalCardDTO dto) {
        this.sessionFactory.getCurrentSession().save(dto);
    }

    @Override
    public List<MedicalCardDTO> getAllEntites() {
        return this.sessionFactory.getCurrentSession().createQuery("from medical_card").list();
    }

    @Override
    public void deleteEntity(Integer id) {
        MedicalCardDTO dto = sessionFactory.getCurrentSession()
                .load(MedicalCardDTO.class, id);
        if (null != dto) {
            this.sessionFactory.getCurrentSession().delete(id);
        }
    }
}
