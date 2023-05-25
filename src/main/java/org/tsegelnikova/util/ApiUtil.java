package org.tsegelnikova.util;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

import java.io.File;

public class ApiUtil {
    private ApiUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static HttpResponse<JsonNode> getRequest(String baseUrl, String endpoint) {
        Unirest.config().defaultBaseUrl(baseUrl);

        return Unirest
                .get(endpoint)
                .asJson();
    }

    public static HttpResponse<JsonNode> postRequest(String endpoint, File file) {
        Unirest.config().defaultBaseUrl("");

        return Unirest
                .post(endpoint)
                .field(ConfigUtil.getTestDataByName("/fileUploadName"), file)
                .asJson();
    }
}
