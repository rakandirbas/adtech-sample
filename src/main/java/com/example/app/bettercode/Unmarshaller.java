package com.example.app.bettercode;

import com.example.app.bettercode.models.AdRequest;
import com.example.app.bettercode.models.AdSize;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Unmarshals json ad requests into an {@link AdRequest}.
 */
public class Unmarshaller {

    private Gson gson;

    public Unmarshaller(Gson gson) {
        this.gson = gson;
    }

    /**
     * Reads a json ad request and returns a {@link AdRequest}.
     *
     * @param adRequest json ad request
     * @return {@link AdRequest}
     */
    public AdRequest process(String adRequest) {
        JsonObject jsonObject = this.gson.fromJson(adRequest, JsonObject.class);

        String adPlaceId = jsonObject.get("adPlaceId").getAsString();
        int width = jsonObject.get("w").getAsInt();
        int height = jsonObject.get("h").getAsInt();
        AdSize adSize = new AdSize(width, height);

        return new AdRequest(adPlaceId, adSize);
    }

}
