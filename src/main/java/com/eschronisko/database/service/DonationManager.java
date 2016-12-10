package com.eschronisko.database.service;

import com.eschronisko.database.dao.ClientDAO;
import com.eschronisko.database.dao.DonationDAO;
import com.eschronisko.database.dto.ClientDTO;
import com.eschronisko.database.dto.DonationDTO;

import java.util.List;

/**
 * Created by Marek on 08.12.2016.
 */
public interface DonationManager  extends ParentManager<DonationDTO, DonationDAO, Integer> {
}
