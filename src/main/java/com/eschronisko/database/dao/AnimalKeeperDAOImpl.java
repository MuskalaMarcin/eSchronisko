package com.eschronisko.database.dao;

import com.eschronisko.database.dto.AnimalKeeperDTO;
import org.springframework.stereotype.Repository;

/**
 * Created by Marek on 08.12.2016.
 */
@Repository
public class AnimalKeeperDAOImpl extends ParentDAOImpl<AnimalKeeperDTO, Integer> implements AnimalKeeperDAO {
    public AnimalKeeperDAOImpl() {
        super(AnimalKeeperDTO.class);
    }
}
