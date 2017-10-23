package com.example.app.bettercode.util;

import com.example.app.bettercode.*;
import com.example.app.bettercode.banner_generators.ImageBannerGenerator;
import com.example.app.bettercode.banner_generators.VideoBannerGenerator;
import com.example.app.bettercode.models.AdPlace;
import com.example.app.bettercode.models.AdSize;
import com.example.app.bettercode.models.AdType;
import com.example.app.bettercode.models.App;
import com.example.app.repositories.AdPlaceRepository;
import com.example.app.repositories.AppRepository;
import com.google.gson.Gson;

/**
 * Facade Singleton.
 */
public final class FacadeSingleton {

    public final static String AD_PLACE_ID = "1f855d85-6b3b-4113-af6b-c87b1b39e185";

    public final static String APP_ID = "2e95de7a-12c3-421f-b6dd-fe19623a3763";

    public final static AdType AD_PLACE_TYPE = AdType.ANIMATION;

    private static Facade instance;

    private FacadeSingleton() {

    }

    public static Facade getInstance() {

        if (instance != null) {

            return instance;

        } else {

            AdPlace adPlace = new AdPlace(AD_PLACE_ID, APP_ID, AD_PLACE_TYPE);
            AdPlaceRepository adPlaceRepository = id -> AD_PLACE_ID.equals(id) ? adPlace : null;

            App app = new App(APP_ID);
            AdSize minSize = new AdSize(100, 100);
            AdSize maxSize = new AdSize(200, 200);
            app.setMinSize(minSize);
            app.setMaxSize(maxSize);

            AppRepository appRepository = id -> APP_ID.equals(id) ? app : null;

            ImageBannerGenerator imageBannerGenerator = new ImageBannerGenerator();
            VideoBannerGenerator videoBannerGenerator = new VideoBannerGenerator();
            BannerService bannerService = new BannerService(
                adPlaceRepository,
                appRepository,
                imageBannerGenerator,
                videoBannerGenerator
            );

            Gson gson = new Gson();
            Unmarshaller unmarshaller = new Unmarshaller(gson);
            Marshaller marshaller = new Marshaller(gson);
            instance = new Facade(unmarshaller, bannerService, marshaller);

            return instance;
        }

    }

}
