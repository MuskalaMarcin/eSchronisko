package com.eschronisko.admin.site;

import org.springframework.stereotype.Service;

import java.util.stream.Stream;

/**
 * Created by Marcin on 11.12.2016.
 */
@Service
public class SiteService {
    public boolean validateViewName(String viewName) {
        return Stream.of(ViewName.values()).anyMatch(p -> p.getName().equals(viewName));
    }
}
