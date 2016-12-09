package com.eschronisko.database.service;

import com.eschronisko.database.dto.DonationDTO;

import java.util.List;

/**
 * Created by Marek on 08.12.2016.
 */
public interface DonationManager {
    public void addEntity(DonationDTO donationDTO);
    public List<DonationDTO> getAllEntites();
    public void deleteEntity(Integer donationId);
}
