package com.example.app.bettercode.models;

public class App {

    private String id;

    private boolean banned;

    private AdSize minSize;

    private AdSize maxSize;

    /**
     * @param id
     */
    public App(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    public AdSize getMinSize() {
        return minSize;
    }

    public void setMinSize(AdSize minSize) {
        this.minSize = minSize;
    }

    public AdSize getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(AdSize maxSize) {
        this.maxSize = maxSize;
    }
}
