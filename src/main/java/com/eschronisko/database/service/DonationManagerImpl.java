package com.eschronisko.database.service;

import com.eschronisko.database.dao.DonationDAO;
import com.eschronisko.database.dto.DonationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Marek on 08.12.2016.
 */
@Service
public class DonationManagerImpl implements DonationManager {
    @Autowired
    private DonationDAO dao;

    @Override
    @Transactional
    public void addEntity(DonationDTO dto) {
        dao.addEntity(dto);
    }

    @Override
    @Transactional
    public List<DonationDTO> getAllEntites() {
        return dao.getAllEntites();
    }

    @Override
    @Transactional
    public void deleteEntity(Integer id) {
        dao.deleteEntity(id);
    }

    public void setDao(DonationDAO dao) {
        this.dao = dao;
    }
}
