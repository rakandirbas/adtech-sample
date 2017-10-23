package com.example.app.repositories;

import com.example.app.bettercode.models.AdPlace;

@FunctionalInterface
public interface AdPlaceRepository {

    /**
     * @return {@link AdPlace} or {@code null} if no {@link AdPlace} found
     */
    AdPlace findById(String id);
    
}
