package com.example.app.bettercode.models;

import com.example.app.bettercode.BannerService;

/**
 * Represents a banner that is returned from the {@link BannerService}.
 */
public class Banner {

    private String url;

    private AdType adType;

    public Banner(String url, AdType adType) {
        this.url = url;
        this.adType = adType;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public AdType getAdType() {
        return adType;
    }

    public void setAdType(AdType adType) {
        this.adType = adType;
    }

}
