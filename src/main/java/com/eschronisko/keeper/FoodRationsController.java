package com.eschronisko.keeper;

import com.eschronisko.common.CommonService;
import com.eschronisko.database.dto.AnimalDTO;
import com.eschronisko.database.dto.FoodRationsDTO;
import com.eschronisko.database.dto.WarehouseDTO;
import com.eschronisko.database.service.AnimalManager;
import com.eschronisko.database.service.FoodRationsManager;
import com.eschronisko.database.service.WarehouseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Marek on 11.12.2016.
 */
@Controller
@RequestMapping("keeper/foodrations")
public class FoodRationsController {

    @Autowired
    private AnimalManager animalManager;
    @Autowired
    private FoodRationsManager foodRationsManager;
    @Autowired
    private WarehouseManager warehouseManager;
    @Autowired
    private CommonService commonService;

    @RequestMapping(method = RequestMethod.GET, value = "addFoodRation")
    public String getAddFoodRationForm(Model model) {
        commonService.getTemplateFragments(model);
        model.addAttribute("content", "keeper/addfoodration");
        model.addAttribute("title", "Dodaj rację żywnościową");
        List<AnimalDTO> animalDTOList = animalManager.getAllEntites().stream()
                .filter(animalDTO -> animalDTO.getFoodRationses() == null).collect(Collectors.toList());
        model.addAttribute("animals", animalDTOList);
        List<WarehouseDTO> warehouseDTOs = warehouseManager.getAllEntites().stream()
                .filter(warehouseDTO -> warehouseDTO.getAmoutLeft() > 0).collect(Collectors.toList());
        model.addAttribute("warehouses", warehouseDTOs);
        return "mainTemplate";
    }

    @RequestMapping(method = RequestMethod.POST, value = "saveFoodRation")
    public String saveFoodRation(@ModelAttribute(value="foodRations") FoodRationsDTO foodRations, BindingResult result,
                                 @RequestParam("animalId") int animalId, @RequestParam("warehouseId") int warehouseId,
                                 Model model) {
        WarehouseDTO warehouseDTO = warehouseManager.getWithId(warehouseId);
        warehouseDTO.setAmoutLeft(warehouseDTO.getAmoutLeft() - foodRations.getAmount());
        foodRations.setAnimal(animalManager.getWithId(animalId));
        foodRations.setWarehouse(warehouseDTO);
        warehouseManager.updateEntity(warehouseDTO);
        foodRationsManager.addEntity(foodRations);
        model.addAttribute("infoContent", "content/info/insertSuccess");
        model.addAttribute("title", "Status dodania obiektu");
        return "infoTemplate";
    }

    @RequestMapping(method = RequestMethod.GET, value = "addWarehouse")
    public String getAddWarehouseForm(Model model) {
        commonService.getTemplateFragments(model);
        model.addAttribute("content", "keeper/addwarehouse");
        model.addAttribute("title", "Dodaj magazyn");
        return "mainTemplate";
    }

    @RequestMapping(method = RequestMethod.POST, value = "saveWarehouse")
    public String saveWarehouse(@ModelAttribute(value="warehouse") WarehouseDTO warehouse, BindingResult result,
                                Model model) {
        warehouse.setAmoutLeft(warehouse.getCapacity());
        warehouseManager.addEntity(warehouse);
        model.addAttribute("infoContent", "content/info/insertSuccess");
        model.addAttribute("title", "Status dodania obiektu");
        return "infoTemplate";
    }

    @RequestMapping(method = RequestMethod.GET, value = "warehousesState")
    public String getWarehousesState(Model model) {
        commonService.getTemplateFragments(model);
        model.addAttribute("content", "keeper/warehousestate");
        model.addAttribute("title", "Stan magazynów");
        model.addAttribute("warehouses", warehouseManager.getAllEntites());
        return "mainTemplate";
    }

    @RequestMapping(method = RequestMethod.GET, value = "foodRationsGranted")
    public String getFoodRationsGranted(Model model) {
        commonService.getTemplateFragments(model);
        model.addAttribute("content", "keeper/foodRationsGranted");
        model.addAttribute("title", "Przyznane racje żywnościowe");
        model.addAttribute("foodRations", foodRationsManager.getAllEntites());
        return "mainTemplate";
    }

    @RequestMapping(value = "/editfoodration/{id}", method = RequestMethod.GET)
    public String editAnimalForm(@PathVariable int id, Model model) {
        commonService.getTemplateFragments(model);
        model.addAttribute("content", "keeper/editFoodRation");
        model.addAttribute("title", "Edytuj rację żywnościową");
        FoodRationsDTO dto = foodRationsManager.getWithId(id);
        model.addAttribute("foodRation", dto);
        return "mainTemplate";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/editfoodration/{id}")
    public String editAnimal(@ModelAttribute("foodRation") FoodRationsDTO dto,
                             @PathVariable int id, Model model) {
        FoodRationsDTO foodRationsDTO = foodRationsManager.getWithId(id);
        WarehouseDTO warehouseDTO = warehouseManager.getWithId(foodRationsDTO.getWarehouse().getId());
        warehouseDTO.setAmoutLeft(warehouseDTO.getAmoutLeft()+foodRationsDTO.getAmount()-dto.getAmount());
        foodRationsDTO.setAmount(dto.getAmount());
        warehouseManager.updateEntity(warehouseDTO);
        foodRationsManager.updateEntity(foodRationsDTO);
        commonService.getTemplateFragments(model);
        model.addAttribute("infoContent", "content/info/editSuccess");
        model.addAttribute("title", "Status edycji");
        return "infoTemplate";
    }

}
