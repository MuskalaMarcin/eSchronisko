package com.eschronisko.database.dao;

import com.eschronisko.database.dto.AdministratorDTO;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Marek on 08.12.2016.
 */
@Repository
public class AdministratorDAOImpl implements AdministratorDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addEntity(AdministratorDTO administratorDTO) {
        this.sessionFactory.getCurrentSession().save(administratorDTO);
    }

    @Override
    public List<AdministratorDTO> getAllEntites() {
        return this.sessionFactory.getCurrentSession().createQuery("from AdministratorDTO").list();
    }

    @Override
    public void deleteEntity(Integer administratorId) {
        AdministratorDTO administratorDTO = sessionFactory.getCurrentSession()
                .load(AdministratorDTO.class, administratorId);
        if (null != administratorDTO) {
            this.sessionFactory.getCurrentSession().delete(administratorDTO);
        }
    }
}
