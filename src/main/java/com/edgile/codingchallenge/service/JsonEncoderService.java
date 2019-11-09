package com.edgile.codingchallenge.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public interface JsonEncoderService {
    JsonObject encode(JsonObject object);

    JsonArray encode(JsonArray array);
}
