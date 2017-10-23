package com.example.app.bettercode.models;

/**
 * Represents an Ad request.
 */
public final class AdRequest {

    private final String adPlaceId;

    private final AdSize adSize;

    public AdRequest(String adPlaceId, AdSize adSize) {
        this.adPlaceId = adPlaceId;
        this.adSize = adSize;
    }

    public String getAdPlaceId() {
        return this.adPlaceId;
    }

    public AdSize getAdSize() {
        return this.adSize;
    }

}
