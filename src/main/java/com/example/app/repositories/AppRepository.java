package com.example.app.repositories;

import com.example.app.bettercode.models.App;

@FunctionalInterface
public interface AppRepository {

    /**
     * @return {@link App} or {@code null} if no {@link App} found
     */
    App findById(String id);
    
}
