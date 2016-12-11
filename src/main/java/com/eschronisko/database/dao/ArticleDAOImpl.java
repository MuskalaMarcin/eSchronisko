package com.eschronisko.database.dao;

import com.eschronisko.database.dto.ArticleDTO;
import org.springframework.stereotype.Repository;

/**
 * @author Marcin Muskala
 * @since 11.12.2016
 */
@Repository
public class ArticleDAOImpl extends ParentDAOImpl<ArticleDTO, String> implements ArticleDAO {
    public ArticleDAOImpl() {
        super(ArticleDTO.class);
    }
}
