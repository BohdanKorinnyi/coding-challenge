package com.edgile.codingchallenge.service.impl;

import com.edgile.codingchallenge.service.EncryptService;
import com.edgile.codingchallenge.service.JsonEncoderService;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Converts all the values of each object in the JSON to a MD5 hash of that value
 */
@Slf4j
@Service
@AllArgsConstructor
public class JsonEncoderServiceImpl implements JsonEncoderService {

    private final EncryptService encryptService;

    /**
     * Converts all the values of object in the JSON object to hash of that value
     *
     * @param object represents JSON object
     * @return transformed JSON object
     */
    @Override
    public JsonObject encode(JsonObject object) {
        JsonObject jsonObject = new JsonObject();
        for (String key : object.keySet()) {
            JsonElement element = object.get(key);
            if (element.isJsonPrimitive()) {
                jsonObject.addProperty(key, encryptService.encode(element.getAsString()));
            } else if (element.isJsonObject()) {
                jsonObject.add(key, encode(element.getAsJsonObject()));
            } else if (element.isJsonArray()) {
                jsonObject.add(key, encode(element.getAsJsonArray()));
            }
        }
        return jsonObject;
    }

    /**
     * Converts all the values of each object in the JSON array to a hash of that value
     *
     * @param array represents JSON array
     * @return transformed JSON array
     */
    @Override
    public JsonArray encode(JsonArray array) {
        JsonArray jsonArray = new JsonArray();
        for (JsonElement element : array) {
            if (element.isJsonPrimitive()) {
                jsonArray.add(encryptService.encode(element.getAsString()));
            } else if (element.isJsonObject()) {
                jsonArray.add(encode(element.getAsJsonObject()));
            } else if (element.isJsonArray()) {
                jsonArray.add(encode(element.getAsJsonArray()));
            }
        }
        return jsonArray;
    }
}
