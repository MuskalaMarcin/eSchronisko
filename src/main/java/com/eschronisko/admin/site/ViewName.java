package com.eschronisko.admin.site;

/**
 * Created by Marcin on 11.12.2016.
 */
public enum ViewName {
    HOME, ADOPTION, VOLUNTEER, TIPS, FOUND, COOPERATION, CONTACT;

    public String getName() {
        return this.toString().toLowerCase();
    }
}
