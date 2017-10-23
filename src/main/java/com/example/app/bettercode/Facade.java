package com.example.app.bettercode;

import com.example.app.bettercode.models.AdRequest;
import com.example.app.bettercode.models.Banner;

/**
 * Facade
 */
public class Facade {

    /**
     * Service to return banners.
     */
    private BannerService bannerService;

    /**
     * Unmarshalls json ad requests into an {@link AdRequest}.
     */
    private Unmarshaller unmarshaller;

    /**
     * Marshalls a {@link Banner} into a proper json response.
     */
    private Marshaller marshaller;

    public Facade(
        Unmarshaller unmarshaller,
        BannerService bannerService,
        Marshaller marshaller
    ) {
        this.bannerService = bannerService;
        this.unmarshaller = unmarshaller;
        this.marshaller = marshaller;
    }

    /**
     * @return JSON with advertisement that client should render
     */
    public String serveAd(String jsonString) {
        String response = "{}";
        try {
            AdRequest adRequest = this.unmarshaller.process(jsonString);
            Banner banner = bannerService.getBanner(adRequest);
            response = this.marshaller.process(banner);
        } catch (Exception exception) {
            //log the exception and update monitoring?
        }

        return response;
    }

}
