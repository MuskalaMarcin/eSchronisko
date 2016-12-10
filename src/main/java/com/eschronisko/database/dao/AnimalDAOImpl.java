package com.eschronisko.database.dao;

import com.eschronisko.database.dto.AnimalDTO;
import org.springframework.stereotype.Repository;

/**
 * Created by Marek on 08.12.2016.
 */
@Repository
public class AnimalDAOImpl extends ParentDAOImpl<AnimalDTO, Integer>  implements AnimalDAO {
    public AnimalDAOImpl() {
        super(AnimalDTO.class);
    }
}
