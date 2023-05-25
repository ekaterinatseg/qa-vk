package org.tsegelnikova.util;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;

public class ConfigUtil {
    private static final ISettingsFile testData = new JsonSettingsFile("TestData.json");
    private static final ISettingsFile settingsData = new JsonSettingsFile("Config.json");

    private ConfigUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static String getTestDataByName(String name) {
        return testData.getValue(name).toString();
    }

    public static String getSettingDataByName(String name) {
        return settingsData.getValue(name).toString();
    }
}
