package com.eschronisko.database.service;

import com.eschronisko.common.Page;
import com.eschronisko.database.dao.AnimalDAO;
import com.eschronisko.database.dto.AnimalDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Marek on 08.12.2016.
 */
@Service
public class AnimalManagerImpl extends ParentManagerImpl<AnimalDTO, AnimalDAO, Integer> implements AnimalManager {
    @Override
    @Transactional
    public Page<AnimalDTO> getAnimalsToAdoption(Integer pageNumber) {
        List<AnimalDTO> animalDTOs = getAllEntites().stream()
                .filter(animalDTO -> animalDTO.getAdoptionPossible() == 1)
                .filter(animalDTO -> animalDTO.getAdoptionDate() == null)
                .collect(Collectors.toList());
        return filterByPage(animalDTOs, pageNumber);
    }
}
