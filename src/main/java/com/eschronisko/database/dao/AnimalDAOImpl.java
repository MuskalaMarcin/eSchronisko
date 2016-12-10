package com.eschronisko.database.dao;

import com.eschronisko.database.dto.AnimalDTO;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Marek on 08.12.2016.
 */
@Repository
public class AnimalDAOImpl implements AnimalDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addEntity(AnimalDTO animalDTO) {
        this.sessionFactory.getCurrentSession().save(animalDTO);
    }

    @Override
    public List<AnimalDTO> getAllEntites() {
        return this.sessionFactory.getCurrentSession().createQuery("from AnimalDTO").list();
    }

    @Override
    public void deleteEntity(Integer animalId) {
        AnimalDTO animalDTO = sessionFactory.getCurrentSession()
                .load(AnimalDTO.class, animalId);
        if (null != animalDTO) {
            this.sessionFactory.getCurrentSession().delete(animalDTO);
        }
    }
}
