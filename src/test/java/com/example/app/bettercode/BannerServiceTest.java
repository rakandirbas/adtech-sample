package com.example.app.bettercode;

import com.example.app.bettercode.banner_generators.ImageBannerGenerator;
import com.example.app.bettercode.banner_generators.VideoBannerGenerator;
import com.example.app.bettercode.models.AdPlace;
import com.example.app.bettercode.models.AdRequest;
import com.example.app.bettercode.models.AdSize;
import com.example.app.bettercode.models.AdType;
import com.example.app.bettercode.models.App;
import com.example.app.bettercode.models.Banner;
import com.example.app.repositories.AdPlaceRepository;
import com.example.app.repositories.AppRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * Tests for {@link BannerService}.
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({
    AdRequest.class,
    SizeSelector.class,
    AdSize.class
})
public class BannerServiceTest {

    /**
     * instance to be tested.
     */
    private BannerService bannerService;

    /**
     * AdPlaceRepository mock.
     */
    @Mock
    private AdPlaceRepository adPlaceRepositoryMock;

    /**
     * AppRepository mock.
     */
    @Mock
    private AppRepository appRepositoryMock;

    /**
     * ImageBannerGenerator mock.
     */
    @Mock
    private ImageBannerGenerator imageBannerGeneratorMock;

    /**
     * AdRequest mock.
     */
    @Mock
    private AdRequest adRequestMock;

    /**
     * VideoBannerGenerator mock.
     */
    @Mock
    private VideoBannerGenerator videoBannerGeneratorMock;

    /**
     * AdPlace mock.
     */
    @Mock
    private AdPlace adPlaceMock;

    /**
     * App mock.
     */
    @Mock
    private App appMock;

    /**
     * AdSize mock.
     */
    @Mock
    private AdSize adSizeMock;

    /**
     * AdPlaceId used in the tests.
     */
    private String adPlaceId = "adPlaceId";

    /**
     * AppId used in the tests.
     */
    private String appId = "appId";

    /**
     * Sets up the test-suite.
     */
    @Before
    public void setup() {
        Mockito.when(adRequestMock.getAdPlaceId()).thenReturn("adPlaceId");
        Mockito.when(adPlaceRepositoryMock.findById(adPlaceId)).thenReturn(adPlaceMock);

        Mockito.when(adPlaceMock.getAppId()).thenReturn("appId");
        Mockito.when(appRepositoryMock.findById(appId)).thenReturn(appMock);

        Mockito.when(appMock.isBanned()).thenReturn(false);

        PowerMockito.mockStatic(SizeSelector.class);
        Mockito.when(
            SizeSelector.select(Mockito.any(AdSize.class), Mockito.any(AdSize.class), Mockito.any(AdSize.class))
        ).thenReturn(adSizeMock);

        bannerService = new BannerService(
            adPlaceRepositoryMock,
            appRepositoryMock,
            imageBannerGeneratorMock,
            videoBannerGeneratorMock
        );

    }

    /**
     * test for {@link BannerService#getBanner(AdRequest)}.
     */
    @Test
    public void getBannerReturnsNullIfAppIsBanned() {
        Mockito.when(appMock.isBanned()).thenReturn(true);
        Banner banner = bannerService.getBanner(adRequestMock);
        Assert.assertNull(banner);
    }

    /**
     * test for {@link BannerService#getBanner(AdRequest)}.
     */
    @Test
    public void getBannerReturnsNullIfNoProperAdSizeCanBeFoundForApp() {
        Mockito.when(
            SizeSelector.select(Mockito.any(AdSize.class), Mockito.any(AdSize.class), Mockito.any(AdSize.class))
        ).thenReturn(null);
        Banner banner = bannerService.getBanner(adRequestMock);
        Assert.assertNull(banner);
    }

    /**
     * test for {@link BannerService#getBanner(AdRequest)}.
     */
    @Test
    public void getBannerWorksCorrectlyWithImageAds() {
        Mockito.when(adPlaceMock.getType()).thenReturn(AdType.IMAGE);
        String expectedUrl = "imageUrl";
        Mockito.when(imageBannerGeneratorMock.generate(AdType.IMAGE, adSizeMock)).thenReturn(expectedUrl);

        Banner banner = bannerService.getBanner(adRequestMock);
        Assert.assertEquals(expectedUrl, banner.getUrl());
        Assert.assertEquals(AdType.IMAGE, banner.getAdType());
    }

    /**
     * test for {@link BannerService#getBanner(AdRequest)}.
     */
    @Test
    public void getBannerWorksCorrectlyWithAnimationAds() {
        Mockito.when(adPlaceMock.getType()).thenReturn(AdType.ANIMATION);
        String expectedUrl = "imageUrl";
        Mockito.when(imageBannerGeneratorMock.generate(AdType.ANIMATION, adSizeMock)).thenReturn(expectedUrl);

        Banner banner = bannerService.getBanner(adRequestMock);
        Assert.assertEquals(expectedUrl, banner.getUrl());
        Assert.assertEquals(AdType.ANIMATION, banner.getAdType());
    }

    /**
     * test for {@link BannerService#getBanner(AdRequest)}.
     */
    @Test
    public void getBannerWorksCorrectlyWithVideoAds() {
        Mockito.when(adPlaceMock.getType()).thenReturn(AdType.VIDEO);
        String expectedUrl = "videoUrl";
        Mockito.when(videoBannerGeneratorMock.generate(adSizeMock)).thenReturn(expectedUrl);

        Banner banner = bannerService.getBanner(adRequestMock);
        Assert.assertEquals(expectedUrl, banner.getUrl());
        Assert.assertEquals(AdType.VIDEO, banner.getAdType());
    }

    /**
     * test for {@link BannerService#setName(String)}.
     */
    @Test
    public void setNameSetsNameOnlyIfProvidedInputIsNotEmpty() {
        String expectedName = "anotherName";
        bannerService.setName(expectedName);
        Assert.assertEquals(expectedName, bannerService.getName());
    }

    /**
     * test for {@link BannerService#setName(String)}.
     */
    @Test
    public void setNameKeepsDefaultNameIfProvidedInputIsEmpty() {
        bannerService.setName("");
        Assert.assertEquals("bannerService", bannerService.getName());
    }

}
