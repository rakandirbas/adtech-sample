package com.example.app.bettercode.banner_generators;


import com.example.app.bettercode.models.AdSize;
import com.example.app.bettercode.models.AdType;

/**
 * Image banner service.
 */
public class ImageBannerGenerator {

    /**
     * Generates advertisement content "on-the-fly". Generated content will fit perfect into ad space size.
     *
     * @param adType type of advertisement
     * @param adSize size of advertisement
     * @return URL of file which should be displayed on client side
     */
    public String generate(AdType adType, AdSize adSize) {
        switch (adType) {
            case IMAGE:
                return "http://cdn101.example.com/img/" + adSize.getHeight() + "x" + adSize.getWidth() + ".png";
            case ANIMATION:
                return "http://cdn202.example.com/img/" + adSize.getHeight() + "x" + adSize.getWidth() + ".gif";
            default:
                throw new RuntimeException("unsupported image banner type!");
        }

    }
    
}
