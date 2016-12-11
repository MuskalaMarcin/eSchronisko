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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
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
    public String saveMedicalCard(@ModelAttribute(value="medicalCard") @Valid MedicalCardDTO medicalCard,
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
    public String saveMedicalTreatment(@ModelAttribute(value="medicalTreatment") @Valid MedicalTreatmentDTO medicalTreatment,
                                  @RequestParam("medicalCardId") int medicalCardId, BindingResult result, Model model) {
        medicalTreatment.setMedicalCard(medicalCardManager.getWithId(medicalCardId));
        medicalTreatment.setStartDate(new Timestamp(System.currentTimeMillis()));
        medicalTreatmentManager.addEntity(medicalTreatment);
        model.addAttribute("infoContent", "content/info/insertSuccess");
        model.addAttribute("title", "Status dodania obiektu");
        return "infoTemplate";
    }

}
