package com.example.app;

import com.example.app.bettercode.Facade;
import com.example.app.bettercode.util.FacadeSingleton;

public class Main {

    public static void main(String... args) {
        String jsonRequest;

        if (args.length == 1) {
            jsonRequest = args[0];
        } else {
            jsonRequest = "{\"adPlaceId\":\""+ FacadeSingleton.AD_PLACE_ID +"\",\"h\":150,\"w\":150}";
        }

        Facade facade = FacadeSingleton.getInstance();
        String jsonResponse = facade.serveAd(jsonRequest);

        System.out.println(jsonResponse);
    }

}

