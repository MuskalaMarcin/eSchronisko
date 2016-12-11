package com.eschronisko.database.service;

import com.eschronisko.database.dao.ArticleDAO;
import com.eschronisko.database.dto.ArticleDTO;

/**
 * @author Marcin Muskala
 * @since 11.12.2016
 */
public interface ArticleManager extends ParentManager<ArticleDTO, ArticleDAO, String> {
}
