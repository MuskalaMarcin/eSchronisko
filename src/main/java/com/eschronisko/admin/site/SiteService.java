package com.eschronisko.admin.site;

import com.eschronisko.common.CommonService;
import com.eschronisko.database.dto.ArticleDTO;
import com.eschronisko.database.service.ArticleManager;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.stream.Stream;

/**
 * Created by Marcin on 11.12.2016.
 */
@Service
public class SiteService {
    @Autowired
    private ArticleManager articleManager;
    @Autowired
    private CommonService commonService;

    public boolean validateViewName(String viewName) {
        return Stream.of(ViewName.values()).anyMatch(p -> p.getName().equals(viewName));
    }

    public void getSiteWithModelAndView(String viewName, Model model) {
        ArticleDTO articleDTO = articleManager.getWithId(viewName);
        commonService.getTemplateFragments(model);
        model.addAttribute("content", articleDTO.getContent());
        model.addAttribute("title", articleDTO.getTitle());
    }

    public void getSiteForEditing(String viewName, Model model) {
        ArticleDTO articleDTO = articleManager.getWithId(viewName);
        commonService.getTemplateFragments(model);
        model.addAttribute("viewName", viewName);
        model.addAttribute("siteContent", articleDTO.getContent());
        model.addAttribute("siteTitle", articleDTO.getTitle());
    }

    public boolean saveChanges(String viewName, String siteContent, String siteTitle) {
        try {
            ArticleDTO articleDTO = articleManager.getWithId(viewName);
            articleDTO.setContent(siteContent);
            articleDTO.setTitle(siteTitle);
            articleManager.updateEntity(articleDTO);
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }
}
