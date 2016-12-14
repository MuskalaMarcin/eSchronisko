package com.eschronisko.vet;

import com.eschronisko.common.CommonService;
import com.eschronisko.database.dto.AnimalDTO;
import com.eschronisko.database.dto.MedicalCardDTO;
import com.eschronisko.database.dto.MedicalTreatmentDTO;
import com.eschronisko.database.service.AnimalManager;
import com.eschronisko.database.service.MedicalCardManager;
import com.eschronisko.database.service.MedicalTreatmentManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Marek on 11.12.2016.
 */
@Controller
@RequestMapping("vet/medicalcare")
public class MedicalCareController {

    @Autowired
    private MedicalCardManager medicalCardManager;
    @Autowired
    private AnimalManager animalManager;
    @Autowired
    private MedicalTreatmentManager medicalTreatmentManager;
    @Autowired
    private CommonService commonService;

    @RequestMapping(method = RequestMethod.GET, value = "addMedicalCard")
    public String getMedicalCardForm(Model model) {
        commonService.getTemplateFragments(model);
        model.addAttribute("content", "vet/addmedicalcard");
        model.addAttribute("title", "Dodaj kartę medyczną");
        List<AnimalDTO> animalDTOList = animalManager.getAllEntites().stream()
                .filter(animalDTO -> animalDTO.getMedicalCard() == null).collect(Collectors.toList());
        model.addAttribute("animals", animalDTOList);
        return "mainTemplate";
    }

    @RequestMapping(method = RequestMethod.POST, value = "saveMedicalCard")
    public String saveMedicalCard(@ModelAttribute(value="medicalCard") MedicalCardDTO medicalCard,
                                  @RequestParam("animalId") int animalId, BindingResult result, Model model) {
        medicalCard.setAnimal(animalManager.getWithId(animalId));
        medicalCardManager.addEntity(medicalCard);
        model.addAttribute("infoContent", "content/info/insertSuccess");
        model.addAttribute("title", "Status dodania obiektu");
        return "infoTemplate";
    }

    @RequestMapping(method = RequestMethod.GET, value = "addMedicalTreatment")
    public String getMedicalTreatmentForm(Model model) {
        commonService.getTemplateFragments(model);
        model.addAttribute("content", "vet/addmedicaltreatment");
        model.addAttribute("title", "Dodaj zabieg");
        model.addAttribute("medicalCards", medicalCardManager.getAllEntites());
        return "mainTemplate";
    }

    @RequestMapping(method = RequestMethod.POST, value = "saveMedicalTreatment")
    public String saveMedicalTreatment(@ModelAttribute(value="medicalTreatment") MedicalTreatmentDTO medicalTreatment,
                                       @RequestParam("medicalCardId") int medicalCardId,
                                       @RequestParam("endDateString") String endDateString,
                                       BindingResult result, Model model) {
        medicalTreatment.setMedicalCard(medicalCardManager.getWithId(medicalCardId));
        medicalTreatment.setStartDate(new Timestamp(System.currentTimeMillis()));
        medicalTreatment.setEndDate(Timestamp.valueOf(endDateString.replace("T", " ") + ":00"));
        medicalTreatmentManager.addEntity(medicalTreatment);
        model.addAttribute("infoContent", "content/info/insertSuccess");
        model.addAttribute("title", "Status dodania obiektu");
        return "infoTemplate";
    }

    @RequestMapping(method = RequestMethod.GET, value = "medicalCards")
    public String getMedicalCardsList(Model model) {
        commonService.getTemplateFragments(model);
        model.addAttribute("content", "vet/medicalCardsList");
        model.addAttribute("title", "Karty medyczne");
        model.addAttribute("medicalCards", medicalCardManager.getAllEntites());
        return "mainTemplate";
    }

    @RequestMapping(method = RequestMethod.GET, value = "medicalTreatments")
    public String getMedicalTreatmentsList(Model model) {
        commonService.getTemplateFragments(model);
        model.addAttribute("content", "vet/medicalTreatmentsList");
        model.addAttribute("title", "Planowane zabiegi");
        model.addAttribute("medicalTreatments", medicalTreatmentManager.getAllEntites());
        return "mainTemplate";
    }

    @RequestMapping(method = RequestMethod.GET, value = "deletetreatment/{id}")
    public String deleteMedicalTreatment(@PathVariable int id, Model model) {
        medicalTreatmentManager.deleteEntity(id);
        commonService.getTemplateFragments(model);
        model.addAttribute("infoContent", "content/info/deleteSuccess");
        model.addAttribute("title", "Status");
        return "infoTemplate";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/editmedicalcard/{id}")
    public String editMedicalCardForm(@PathVariable int id, Model model) {
        commonService.getTemplateFragments(model);
        model.addAttribute("content", "vet/editMedicalCard");
        model.addAttribute("title", "Edytuj kartę nr " + id);
        model.addAttribute("medicalCard", medicalCardManager.getWithId(id));
        return "mainTemplate";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/editmedicalcard/{id}")
    public String editMedicalCard(@ModelAttribute("medicalCard") MedicalCardDTO dto,
                                  @PathVariable int id, Model model) {
        dto.setAnimal(animalManager.getWithId(id));
        medicalCardManager.updateEntity(dto);
        commonService.getTemplateFragments(model);
        model.addAttribute("infoContent", "content/info/editSuccess");
        model.addAttribute("title", "Status edycji");
        return "infoTemplate";
    }

    @RequestMapping(method = RequestMethod.GET, value = "animalsList")
    public String getAnimalsList(Model model) {
        commonService.getTemplateFragments(model);
        model.addAttribute("content", "vet/animalsList");
        model.addAttribute("title", "Lista zwierząt");
        model.addAttribute("animals", animalManager.getAllEntites());
        return "mainTemplate";
    }

    @RequestMapping(value = "/editadoptionpossible/{id}", method = RequestMethod.GET)
    public String editAnimalForm(@PathVariable int id, Model model) {
        commonService.getTemplateFragments(model);
        model.addAttribute("content", "vet/editAnimal");
        model.addAttribute("title", "Edytuj możliwość adopcji zwierzęcia");
        model.addAttribute("animal", animalManager.getWithId(id));
        return "mainTemplate";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/editadoptionpossible/{id}")
    public String editAnimal(@ModelAttribute("medicalCard") AnimalDTO dto,
                                  @PathVariable int id, Model model) {
        AnimalDTO animalDTO = animalManager.getWithId(id);
        animalDTO.setAdoptionPossible(dto.getAdoptionPossible());
        animalManager.updateEntity(animalDTO);
        commonService.getTemplateFragments(model);
        model.addAttribute("infoContent", "content/info/editSuccess");
        model.addAttribute("title", "Status edycji");
        return "infoTemplate";
    }

}
