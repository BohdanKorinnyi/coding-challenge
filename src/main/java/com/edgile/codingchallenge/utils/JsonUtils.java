package com.edgile.codingchallenge.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonUtils {
    public static Optional<JsonElement> parseString(String value) {
        try {
            return Optional.of(JsonParser.parseString(value));
        } catch (Exception e) {
            log.error("An error occurred while parsing string: {}", value);
        }
        return Optional.empty();
    }

    public static boolean isProcessable(JsonElement element) {
        return element.isJsonObject() || element.isJsonArray();
    }

    public static Optional<JsonObject> toObject(JsonElement element) {
        return element.isJsonObject() ? Optional.of(element.getAsJsonObject()) : Optional.empty();
    }

    public static Optional<JsonArray> toArray(JsonElement element) {
        return element.isJsonArray() ? Optional.of(element.getAsJsonArray()) : Optional.empty();
    }
}
