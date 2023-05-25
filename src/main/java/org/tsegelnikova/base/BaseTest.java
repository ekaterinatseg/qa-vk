package org.tsegelnikova.base;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.tsegelnikova.util.ConfigUtil;

public interface BaseTest {

    @BeforeMethod
    default void setUp() {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        Browser browser = AqualityServices.getBrowser();
        browser.maximize();
        browser.goTo(ConfigUtil.getSettingDataByName("/url"));
        browser.waitForPageToLoad();
    }

    @AfterMethod
    default void tearDown() {
        AqualityServices.getBrowser().quit();
    }
}
