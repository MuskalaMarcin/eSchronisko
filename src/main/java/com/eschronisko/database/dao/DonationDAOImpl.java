package com.eschronisko.database.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.eschronisko.database.dto.DonationDTO;
import org.springframework.stereotype.Repository;

/**
 * Created by Marek on 08.12.2016.
 */
@Repository
public class DonationDAOImpl implements DonationDAO {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public void addEntity(DonationDTO donationDTO) {
        this.sessionFactory.getCurrentSession().save(donationDTO);
    }

    @Override
    public List<DonationDTO> getAllEntites() {
        return this.sessionFactory.getCurrentSession().createQuery("from donation").list();
    }

    @Override
    public void deleteEntity(Integer id) {
        DonationDTO donationDTO = sessionFactory.getCurrentSession()
                .load(DonationDTO.class, id);
        if (null != donationDTO) {
            this.sessionFactory.getCurrentSession().delete(id);
        }
    }
}
