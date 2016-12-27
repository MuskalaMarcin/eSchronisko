package com.eschronisko.common;

import java.util.List;

/**
 * Created by Marcin on 27.12.2016.
 */
public class Page<A> {
    private Integer pageNumber;
    private Integer totalPages;
    private List<A> content;

    public Page(Integer pageNumber, Integer totalPages, List<A> content) {
        this.pageNumber = pageNumber;
        this.totalPages = totalPages;
        this.content = content;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public List<A> getContent() {
        return content;
    }
}
