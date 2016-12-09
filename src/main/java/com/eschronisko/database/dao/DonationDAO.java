package com.eschronisko.database.dao;

import com.eschronisko.database.dto.DonationDTO;

import java.util.List;

/**
 * Created by Marek on 08.12.2016.
 */
public interface DonationDAO {
    public void addEntity(DonationDTO donationDTO);
    public List<DonationDTO> getAllEntites();
    public void deleteEntity(Integer donationId);
}
