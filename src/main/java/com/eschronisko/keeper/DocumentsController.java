package com.eschronisko.keeper;

import com.eschronisko.common.CommonService;
import com.eschronisko.database.dto.AnimalDTO;
import com.eschronisko.database.dto.ApplicationDTO;
import com.eschronisko.database.service.AnimalManager;
import com.eschronisko.database.service.ApplicationManager;
import com.eschronisko.database.service.DonationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;

/**
 * Created by Marek on 13.12.2016.
 */
@Controller
@RequestMapping("keeper")
public class DocumentsController {

    @Autowired
    private AnimalManager animalManager;
    @Autowired
    private ApplicationManager applicationManager;
    @Autowired
    private DonationManager donationManager;
    @Autowired
    private CommonService commonService;

    @RequestMapping(method = RequestMethod.GET, value = "donationsList")
    public String getDonationsList(@RequestParam(required = false, defaultValue = "1", value = "page") Integer page, Model model) {
        commonService.getTemplateFragments(model);
        model.addAttribute("content", "keeper/donationsList");
        model.addAttribute("title", "Dotacje");
        model.addAttribute("donations", donationManager.getAllEntites(page));
        return "mainTemplate";
    }

    @RequestMapping(method = RequestMethod.GET, value = "applicationsList")
    public String getApplicationsList(@RequestParam(required = false, defaultValue = "1", value = "page") Integer page, Model model) {
        commonService.getTemplateFragments(model);
        model.addAttribute("content", "keeper/applicationsList");
        model.addAttribute("title", "Wnioski");
        model.addAttribute("applications", applicationManager.getWaitingApplications(page));
        return "mainTemplate";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/acceptApplication/{id}")
    public String acceptApplication(@PathVariable int id, Model model) {
        ApplicationDTO applicationDTO = applicationManager.getWithId(id);
        applicationDTO.setStatus("Zaakceptowany");
        AnimalDTO animalDTO = animalManager.getWithId(applicationDTO.getAnimal().getRegistrationNumber());
        animalDTO.setAdoptionDate(new Timestamp(System.currentTimeMillis()));
        animalManager.updateEntity(animalDTO);
        applicationManager.updateEntity(applicationDTO);
        commonService.getTemplateFragments(model);
        model.addAttribute("infoContent", "content/info/decisionSent");
        model.addAttribute("title", "Status decyzji");
        return "infoTemplate";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/refuseApplication/{id}")
    public String refuseApplication(@PathVariable int id, Model model) {
        ApplicationDTO applicationDTO = applicationManager.getWithId(id);
        applicationDTO.setStatus("Odrzucony");
        applicationManager.updateEntity(applicationDTO);
        commonService.getTemplateFragments(model);
        model.addAttribute("infoContent", "content/info/decisionSent");
        model.addAttribute("title", "Status decyzji");
        return "infoTemplate";
    }

}
