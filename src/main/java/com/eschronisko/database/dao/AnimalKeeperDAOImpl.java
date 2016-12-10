package com.eschronisko.database.dao;

import com.eschronisko.database.dto.AnimalKeeperDTO;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Marek on 08.12.2016.
 */
@Repository
public class AnimalKeeperDAOImpl implements AnimalKeeperDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addEntity(AnimalKeeperDTO animalKeeperDTO) {
        this.sessionFactory.getCurrentSession().save(animalKeeperDTO);
    }

    @Override
    public List<AnimalKeeperDTO> getAllEntites() {
        return this.sessionFactory.getCurrentSession().createQuery("from AnimalKeeperDTO").list();
    }

    @Override
    public void deleteEntity(Integer animalKeeperId) {
        AnimalKeeperDTO animalDTO = sessionFactory.getCurrentSession()
                .load(AnimalKeeperDTO.class, animalKeeperId);
        if (null != animalDTO) {
            this.sessionFactory.getCurrentSession().delete(animalKeeperId);
        }
    }
}
