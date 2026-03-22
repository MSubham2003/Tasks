package com.subham.user_auth_svc.enums;

public enum Role {
    USER("This is a USER"),
    ADMIN("Ths is an ADMIN");

    private final String description;

    // Constructor
    Role(String description) {
        this.description = description;
    }

    // Getter for the description
    public String getDescription() {
        return description;
    }
}