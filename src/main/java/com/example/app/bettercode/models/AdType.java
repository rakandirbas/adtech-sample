package com.example.app.bettercode.models;

/**
 * Represents an Ad type.
 */
public enum AdType {

    IMAGE("image"),

    ANIMATION("animation"),

    VIDEO("video");

    private final String value;

    AdType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
