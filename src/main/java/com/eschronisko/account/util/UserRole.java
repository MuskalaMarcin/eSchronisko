package com.eschronisko.account.util;

/**
 * Created by Marcin on 10.12.2016.
 */
public enum UserRole {
    ADMIN("Administrator"), VET("Weterynarz"), KEEPER("Opiekun"), CLIENT("Klient");

    private String displayName;

    UserRole(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
