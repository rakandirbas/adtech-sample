package com.example.app.bettercode.models;

public class AdPlace {

    private String id;

    private String appId;

    private AdType type;

    /**
     * @param id
     * @param appId
     * @param type
     */
    public AdPlace(String id, String appId, AdType type) {
        this.id = id;
        this.appId = appId;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public AdType getType() {
        return this.type;
    }

    public void setType(AdType type) {
        this.type = type;
    }
}
