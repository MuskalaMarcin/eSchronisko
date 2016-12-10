package com.eschronisko.database.service;

import com.eschronisko.database.dao.DonationDAO;
import com.eschronisko.database.dto.DonationDTO;
import org.springframework.stereotype.Service;

/**
 * Created by Marek on 08.12.2016.
 */
@Service
public class DonationManagerImpl extends ParentManagerImpl<DonationDTO, DonationDAO, Integer> implements DonationManager {
}
