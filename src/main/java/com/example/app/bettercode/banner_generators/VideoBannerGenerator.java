package com.example.app.bettercode.banner_generators;


import com.example.app.bettercode.models.AdSize;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Generates video banners.
 */
public class VideoBannerGenerator {

    private final List<AdSize> fixedVideoSizes;

    public VideoBannerGenerator() {
        this.fixedVideoSizes = Collections.synchronizedList(new ArrayList<AdSize>());
        this.fixedVideoSizes.add(new AdSize(320, 240));
        this.fixedVideoSizes.add(new AdSize(640, 480));
        this.fixedVideoSizes.add(new AdSize(1280, 720));
        this.fixedVideoSizes.add(new AdSize(1440, 720));
    }

    /**
     * Generates video url that corresponds to a proper video size.
     *
     * @param adSize size of advertisement
     * @return URL of file which should be displayed on client side
     */
    public String generate(AdSize adSize) {
        AdSize selectedSize = this.fixedVideoSizes.get(0);
        double minDistance = this.getDistance(
            adSize.getWidth(),
            adSize.getHeight(),
            selectedSize.getWidth(),
            selectedSize.getHeight()
        );

        for (AdSize fixedSize : this.fixedVideoSizes) {
            double distance = this.getDistance(
                adSize.getWidth(),
                adSize.getHeight(),
                fixedSize.getWidth(),
                fixedSize.getHeight()
            );
            if (distance < minDistance) {
                minDistance = distance;
                selectedSize = fixedSize;
            }
        }

        return String.format(
            "http://cdn303.example.com/video/codec/mp4/%d-%d.mp4",
            selectedSize.getHeight(), selectedSize.getWidth()
        );
    }

    public void addSize(AdSize addSize) {
        this.fixedVideoSizes.add(addSize);
    }

    /**
     * Calculates the Euclidean distance between two points.
     *
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return
     */
    private double getDistance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(((x1 - x2) * (x1 - x2)) + ((y1 - y2) * (y1 - y2)));
    }

}
