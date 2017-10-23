package com.example.app.bettercode;

import com.example.app.bettercode.models.Banner;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Marshalls a {@link Banner} into a proper json response.
 */
public class Marshaller {

    private Gson gson;

    public Marshaller(Gson gson) {
        this.gson = gson;
    }

    public String process(Banner banner) {
        if (banner == null) {
            return "{}";
        }

        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("url", banner.getUrl());
        jsonResponse.addProperty("type", banner.getAdType().getValue());

        return jsonResponse.toString();
    }

}
