package com.eschronisko.account.util;

import java.util.stream.Stream;

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

    public static UserRole getUserRole(String roleString) {
        return Stream.of(values()).filter(f -> f.toString().equals(roleString.toUpperCase())).findAny().orElse(null);
    }
}
