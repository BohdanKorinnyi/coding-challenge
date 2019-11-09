package com.edgile.codingchallenge.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class JsonUtilsTest {

    @Test
    void parseStringInvalidJsonShouldReturnOptionalEmpty() {
        String invalidJson = "{{}";
        Optional<JsonElement> actual = JsonUtils.parseString(invalidJson);
        assertThat(actual).isEmpty();
    }

    @Test
    void parseStringEmptyJsonObjectShouldReturnEmptyJsonObject() {
        String emptyJsonObject = "{}";
        Optional<JsonElement> actual = JsonUtils.parseString(emptyJsonObject);
        assertThat(actual).isNotEmpty();
        assertThat(actual.get()).isEqualTo(new JsonObject());
    }

    @Test
    void parseStringEmptyJsonObjectShouldReturnJsonObject() {
        String jsonObject = "{\"key\": 2}";
        Optional<JsonElement> actual = JsonUtils.parseString(jsonObject);
        JsonObject expected = new JsonObject();
        expected.addProperty("key", 2);
        assertThat(actual).isNotEmpty();
        assertThat(actual.get()).isEqualTo(expected);
    }

    @Test
    void parseStringEmptyJsonObjectShouldReturnJsonArray() {
        String jsonObject = "[{\"key\": 2}]";
        Optional<JsonElement> actual = JsonUtils.parseString(jsonObject);
        JsonObject expectedObject = new JsonObject();
        expectedObject.addProperty("key", 2);
        JsonArray expectedArray = new JsonArray();
        expectedArray.add(expectedObject);
        assertThat(actual).isNotEmpty();
        assertThat(actual.get()).isEqualTo(expectedArray);
    }

    @Test
    void parseStringEmptyJsonObjectShouldReturnJsonArrayOfPrimitives() {
        String jsonObject = "[1,2,3,\"4\"]";
        Optional<JsonElement> actual = JsonUtils.parseString(jsonObject);
        JsonArray expectedArray = new JsonArray();
        expectedArray.add(new JsonPrimitive(1));
        expectedArray.add(new JsonPrimitive(2));
        expectedArray.add(new JsonPrimitive(3));
        expectedArray.add(new JsonPrimitive("4"));
        assertThat(actual).isNotEmpty();
        assertThat(actual.get()).isEqualTo(expectedArray);
    }

    @Test
    void isProcessableShouldReturnFalseForJsonNull() {
        assertThat(JsonUtils.isProcessable(JsonNull.INSTANCE)).isFalse();
    }

    @Test
    void isProcessableShouldReturnTrueForJsonObject() {
        assertThat(JsonUtils.isProcessable(new JsonObject())).isTrue();
    }

    @Test
    void isProcessableShouldReturnTrueForJsonArray() {
        assertThat(JsonUtils.isProcessable(new JsonArray())).isTrue();
    }

    @Test
    void isProcessableShouldReturnTrueForJsonPrimitive() {
        assertThat(JsonUtils.isProcessable(new JsonArray())).isTrue();
    }

    @Test
    void toObjectShouldReturnOptimalEmptyForNotObject() {
        assertThat(JsonUtils.toObject(new JsonPrimitive(0))).isEmpty();
    }

    @Test
    void toObjectShouldReturnObject() {
        assertThat(JsonUtils.toObject(new JsonObject())).isPresent();
    }

    @Test
    void toArrayShouldReturnOptimalEmptyForNotArray() {
        assertThat(JsonUtils.toObject(new JsonPrimitive(0))).isEmpty();
    }

    @Test
    void toArrayShouldReturnObject() {
        assertThat(JsonUtils.toArray(new JsonArray())).isPresent();
    }
}