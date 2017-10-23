package com.example.app.bettercode;

import com.example.app.bettercode.banner_generators.ImageBannerGenerator;
import com.example.app.bettercode.banner_generators.VideoBannerGenerator;
import com.example.app.bettercode.models.AdPlace;
import com.example.app.bettercode.models.AdRequest;
import com.example.app.bettercode.models.AdSize;
import com.example.app.bettercode.models.App;
import com.example.app.bettercode.models.Banner;
import com.example.app.repositories.AdPlaceRepository;
import com.example.app.repositories.AppRepository;

/**
 * Banner service.
 */
public class BannerService {

    private AdPlaceRepository adPlaceRepository;

    private AppRepository appRepository;

    private ImageBannerGenerator imageBannerGenerator;

    private VideoBannerGenerator videoBannerGenerator;

    private String name = "bannerService";

    private int runtime = 15000;

    public BannerService (
        AdPlaceRepository adPlaceRepository,
        AppRepository appRepository,
        ImageBannerGenerator imageBannerGenerator,
        VideoBannerGenerator videoBannerGenerator
    ) {
        this.adPlaceRepository = adPlaceRepository;
        this.appRepository = appRepository;
        this.imageBannerGenerator = imageBannerGenerator;
        this.videoBannerGenerator = videoBannerGenerator;
    }

    /**
     * @return {@link Banner} that is a response to an {@link AdRequest}.
     * If no proper {@link Banner} is found, null is returned.
     */
    public Banner getBanner(AdRequest adRequest) {
        AdPlace adPlace = adPlaceRepository.findById(adRequest.getAdPlaceId());
        App app = appRepository.findById(adPlace.getAppId());

        if (app.isBanned()) {
            return null;
        }

        AdSize properSize = SizeSelector.select(adRequest.getAdSize(), app.getMinSize(), app.getMaxSize());
        if (properSize == null) {
            return null;
        }

        String url;
        switch (adPlace.getType()) {
            case IMAGE:
                url = imageBannerGenerator.generate(adPlace.getType(), properSize);
                break;
            case ANIMATION:
                url = imageBannerGenerator.generate(adPlace.getType(), properSize);
                break;
            case VIDEO:
                url = videoBannerGenerator.generate(properSize);
                break;
            default:
                throw new RuntimeException("unsupported banner type!");
        }

        return new Banner(url, adPlace.getType());

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (!name.isEmpty()) {
            this.name = name;
        }
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }
}
