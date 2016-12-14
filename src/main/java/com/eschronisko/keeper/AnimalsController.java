package com.eschronisko.keeper;

import com.eschronisko.common.CommonService;
import com.eschronisko.database.dto.AnimalDTO;
import com.eschronisko.database.service.AnimalManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping(method = RequestMethod.GET, value = "deleteanimal/{id}")
    public String deleteAnimal(@PathVariable int id, Model model) {
        animalManager.deleteEntity(id);
        commonService.getTemplateFragments(model);
        model.addAttribute("infoContent", "content/info/deleteSuccess");
        model.addAttribute("title", "Status");
        return "infoTemplate";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/editanimal/{id}")
    public String editAnimalForm(@PathVariable int id, Model model) {
        AnimalDTO dto = animalManager.getWithId(id);
        commonService.getTemplateFragments(model);
        model.addAttribute("content", "keeper/editAnimal");
        model.addAttribute("title", "Edytuj zwierzę " + dto.getName());
        model.addAttribute("animal", dto);
        return "mainTemplate";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/editanimal/{id}")
    public String editAnimal(@ModelAttribute("animal") AnimalDTO dto,
                                  @PathVariable int id, Model model) {
        AnimalDTO animalDTO = animalManager.getWithId(id);
        animalDTO.setAge(dto.getAge());
        animalDTO.setLinkToImage(dto.getLinkToImage());
        animalDTO.setAdoptionPossible(dto.getAdoptionPossible());
        animalDTO.setRoomNumber(dto.getRoomNumber());
        animalManager.updateEntity(animalDTO);
        commonService.getTemplateFragments(model);
        model.addAttribute("infoContent", "content/info/editSuccess");
        model.addAttribute("title", "Status edycji");
        return "infoTemplate";
    }

}
