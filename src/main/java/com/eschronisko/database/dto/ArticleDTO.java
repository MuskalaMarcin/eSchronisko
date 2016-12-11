package com.eschronisko.database.dto;

import javax.persistence.*;

/**
 * @author Marcin Muskala
 * @since 11.12.2016
 */
@Entity
@Table(name = "article", schema = "public", catalog = "eschronisko")
public class ArticleDTO extends ParentDTO {
    private String id;
    private String title;
    private String content;

    @Id
    @Column(name = "id", nullable = false, length = 64)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title", nullable = false, length = 256)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "content", nullable = false)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
