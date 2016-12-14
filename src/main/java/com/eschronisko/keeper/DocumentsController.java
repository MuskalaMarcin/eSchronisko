package com.eschronisko.keeper;

import com.eschronisko.common.CommonService;
import com.eschronisko.database.dto.ApplicationDTO;
import com.eschronisko.database.service.ApplicationManager;
import com.eschronisko.database.service.DonationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Marek on 13.12.2016.
 */
@Controller
@RequestMapping("keeper")
public class DocumentsController {

    @Autowired
    private ApplicationManager applicationManager;
    @Autowired
    private DonationManager donationManager;
    @Autowired
    private CommonService commonService;

    @RequestMapping(method = RequestMethod.GET, value = "donationsList")
    public String getDonationsList(Model model) {
        commonService.getTemplateFragments(model);
        model.addAttribute("content", "keeper/donationsList");
        model.addAttribute("title", "Dotacje");
        model.addAttribute("donations", donationManager.getAllEntites());
        return "mainTemplate";
    }

    @RequestMapping(method = RequestMethod.GET, value = "applicationsList")
    public String getApplicationsList(Model model) {
        commonService.getTemplateFragments(model);
        model.addAttribute("content", "keeper/applicationsList");
        model.addAttribute("title", "Wnioski");
        List<ApplicationDTO> applicationDTOList = applicationManager.getAllEntites().stream()
                .filter(applicationDTO -> applicationDTO.getStatus().equals("Oczekujący")).collect(Collectors.toList());
        model.addAttribute("applications", applicationDTOList);
        return "mainTemplate";
    }

}
