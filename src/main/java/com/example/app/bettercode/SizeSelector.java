package com.example.app.bettercode;

import com.example.app.bettercode.models.AdSize;

/**
 * Selects a proper {@link AdSize} for a banner.
 */
public class SizeSelector {

    /**
     * Returns a proper {@link AdSize} selected according to a set of min/max constraints.
     * If a real size doesn't fit the constraints, null is returned.
     *
     * @param realSize
     * @param minSize
     * @param maxSize
     *
     * @return {@link AdSize}
     */
    public static AdSize select(AdSize realSize, AdSize minSize, AdSize maxSize) {

        if (minSize.getHeight() != null && minSize.getWidth() != null) {
            if (realSize.getHeight() < minSize.getHeight() || realSize.getWidth() < minSize.getWidth()) {
                return null;
            }
        }

        if (
            maxSize.getHeight() != null && maxSize.getWidth() != null
            && (realSize.getHeight() > maxSize.getHeight() || realSize.getWidth() > maxSize.getWidth())
        ) {
            return new AdSize(maxSize.getWidth(), maxSize.getHeight());
        } else {
            return new AdSize(realSize.getWidth(), realSize.getHeight());
        }

    }

}
