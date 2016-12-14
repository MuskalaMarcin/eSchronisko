package com.eschronisko.keeper;

import com.eschronisko.common.CommonService;
import com.eschronisko.database.dto.AnimalDTO;
import com.eschronisko.database.service.AnimalManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Timestamp;

/**
 * Created by Marek on 10.12.2016.
 */
@Controller
@RequestMapping("keeper/animals")
public class AnimalsController {

    @Autowired
    private AnimalManager animalManager;
    @Autowired
    private CommonService commonService;

    @RequestMapping(method = RequestMethod.GET, value = "addAnimal")
    public String getAddAnimalForm(Model model) {
        commonService.getTemplateFragments(model);
        model.addAttribute("content", "keeper/addanimal");
        model.addAttribute("title", "Dodaj zwierzę");
        return "mainTemplate";
    }

    @RequestMapping(method = RequestMethod.POST, value = "saveAnimal")
    public String saveAnimal(@ModelAttribute(value="animal") AnimalDTO animal, BindingResult result, Model model) {
        animal.setAcceptanceDate(new Timestamp(System.currentTimeMillis()));
        animalManager.addEntity(animal);
        model.addAttribute("infoContent", "content/info/insertSuccess");
        model.addAttribute("title", "Status dodania obiektu");
        return "infoTemplate";
    }

    @RequestMapping(method = RequestMethod.GET, value = "animalsList")
    public String animalsList(Model model) {
        commonService.getTemplateFragments(model);
        model.addAttribute("content", "keeper/animalsList");
        model.addAttribute("title", "Lista zwierząt");
        model.addAttribute("animals", animalManager.getAllEntites());
        return "mainTemplate";
    }

}
