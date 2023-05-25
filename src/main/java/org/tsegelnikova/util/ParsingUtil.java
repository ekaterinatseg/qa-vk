package org.tsegelnikova.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ParsingUtil {
    private ParsingUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static <T> T parsingObject(String json, Class<T> type) {
        if (json.isEmpty() || json.equals("{}")) {
            return null;
        }

        T output;

        try {
            output = new ObjectMapper().readValue(json, type);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return output;
    }
}
