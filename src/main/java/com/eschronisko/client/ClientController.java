package com.eschronisko.client;

import com.eschronisko.common.CommonService;
import com.eschronisko.database.dto.ApplicationDTO;
import com.eschronisko.database.dto.ClientDTO;
import com.eschronisko.database.dto.DonationDTO;
import com.eschronisko.database.service.AnimalManager;
import com.eschronisko.database.service.ApplicationManager;
import com.eschronisko.database.service.DonationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Marek on 13.12.2016.
 */
@Controller
@RequestMapping("client")
public class ClientController {

    @Autowired
    private AnimalManager animalManager;
    @Autowired
    private ApplicationManager applicationManager;
    @Autowired
    private DonationManager donationManager;
    @Autowired
    private CommonService commonService;

    @RequestMapping(method = RequestMethod.GET, value = "animalsList")
    public String getAnimalsList(Model model) {
        commonService.getTemplateFragments(model);
        model.addAttribute("content", "client/animalsList");
        model.addAttribute("title", "Lista zwierząt");
        model.addAttribute("animals", animalManager.getAllEntites());
        return "mainTemplate";
    }

    @RequestMapping(method = RequestMethod.GET, value = "adopt/{registrationNumber}")
    public String adoptAnimal(@PathVariable int registrationNumber, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ApplicationDTO dto = new ApplicationDTO();
        dto.setAnimal(animalManager.getWithId(registrationNumber));
        dto.setClient(commonService.getLoggedUser(auth).getClient());
        dto.setDispatchDate(new Timestamp(System.currentTimeMillis()));
        dto.setStatus("Oczekujący");
        applicationManager.addEntity(dto);
        commonService.getTemplateFragments(model);
        model.addAttribute("infoContent", "content/info/applicationSent");
        model.addAttribute("title", "Status podania");
        return "infoTemplate";
    }

    @RequestMapping(method = RequestMethod.GET, value = "donateForm")
    public String getDonationForm(Model model) {
        commonService.getTemplateFragments(model);
        model.addAttribute("content", "client/donate");
        model.addAttribute("title", "Wyślij dotację");
        return "mainTemplate";
    }

    @RequestMapping(method = RequestMethod.POST, value = "donate")
    public String donate(@ModelAttribute DonationDTO dto, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        dto.setClient(commonService.getLoggedUser(auth).getClient());
        dto.setDonationDate(new Timestamp(System.currentTimeMillis()));
        donationManager.addEntity(dto);
        commonService.getTemplateFragments(model);
        model.addAttribute("infoContent", "content/info/donationSent");
        model.addAttribute("title", "Status dotacji");
        return "infoTemplate";
    }

    @RequestMapping(method = RequestMethod.GET, value = "applicationsList")
    public String getApplicationsList(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ClientDTO clientDTO = commonService.getLoggedUser(auth).getClient();
        List<ApplicationDTO> applicationDTOList = applicationManager.getAllEntites().stream().filter(applicationDTO ->
                applicationDTO.getClient().equals(clientDTO)).collect(Collectors.toList());
        commonService.getTemplateFragments(model);
        model.addAttribute("content", "client/applicationsList");
        model.addAttribute("title", "Wysłane wnioski");
        model.addAttribute("applications", applicationDTOList);
        return "mainTemplate";
    }

}
