package com.eschronisko.database.dao;

import com.eschronisko.database.dto.MedicalTreatmentDTO;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Marek on 09.12.2016.
 */
@Repository
public class MedicalTreatmentDAOImpl implements MedicalTreatmentDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addEntity(MedicalTreatmentDTO dto) {
        this.sessionFactory.getCurrentSession().save(dto);
    }

    @Override
    public List<MedicalTreatmentDTO> getAllEntites() {
        return this.sessionFactory.getCurrentSession().createQuery("from MedicalTreatmentDTO").list();
    }

    @Override
    public void deleteEntity(Integer id) {
        MedicalTreatmentDTO dto = sessionFactory.getCurrentSession()
                .load(MedicalTreatmentDTO.class, id);
        if (null != dto) {
            this.sessionFactory.getCurrentSession().delete(id);
        }
    }
}
