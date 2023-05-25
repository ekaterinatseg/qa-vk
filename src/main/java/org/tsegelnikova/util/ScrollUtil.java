package org.tsegelnikova.util;

import aquality.selenium.browser.AqualityServices;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

public class ScrollUtil {
    private ScrollUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static void scrollToElement(By path) {
        JavascriptExecutor js = AqualityServices.getBrowser().getDriver();
        js.executeScript("arguments[0].scrollIntoView();", AqualityServices.getBrowser().getDriver().findElement(path));
    }
}
