package com.eschronisko.database.service;

import com.eschronisko.database.dao.AnimalDAO;
import com.eschronisko.database.dto.AnimalDTO;

import java.util.List;

/**
 * Created by Marek on 08.12.2016.
 */
public interface AnimalManager extends ParentManager<AnimalDTO, AnimalDAO, Integer>{
}
