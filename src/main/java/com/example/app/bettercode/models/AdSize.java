package com.example.app.bettercode.models;

/**
 * Represents an AdSize.
 */
public final class AdSize {

    private final Integer width;

    private final Integer height;

    public AdSize(Integer width, Integer height) {
        this.width = width;
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getHeight() {
        return height;
    }
}
