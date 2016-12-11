package com.eschronisko.database.service;

import com.eschronisko.database.dao.ArticleDAO;
import com.eschronisko.database.dto.ArticleDTO;
import org.springframework.stereotype.Service;

/**
 * @author Marcin Muskala
 * @since 11.12.2016
 */
@Service
public class ArticleManagerImpl extends ParentManagerImpl<ArticleDTO, ArticleDAO, String> implements ArticleManager {
}
