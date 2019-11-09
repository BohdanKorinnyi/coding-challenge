package com.edgile.codingchallenge.service.impl;

import com.edgile.codingchallenge.service.EncryptService;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.nio.file.Files;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JsonEncoderServiceImplTest {

    private static final String NUMBER_2_IN_MD5_HASH = "C81E728D9D4C2F636F067F89CC14862C";
    private static final String WORD_TEXT_IN_MD5_HASH = "1CB251EC0D568DE6A929B520C4AED8D1";

    @Mock
    private EncryptService encryptService;
    @InjectMocks
    private JsonEncoderServiceImpl jsonEncoderService;

    @Test
    void encodeShouldProcessJsonObjectValues() {
        JsonElement element = readFromFile("json_object.json");
        when(encryptService.encode("2")).thenReturn(NUMBER_2_IN_MD5_HASH);
        when(encryptService.encode("text")).thenReturn(WORD_TEXT_IN_MD5_HASH);
        JsonObject actual = jsonEncoderService.encode(element.getAsJsonObject());
        assertThat(actual).isEqualTo(readFromFile("json_object_expected.json"));
    }

    @Test
    void encodeShouldProcessJsonArrayValues() {
        JsonElement element = readFromFile("json_array.json");
        when(encryptService.encode("2")).thenReturn(NUMBER_2_IN_MD5_HASH);
        when(encryptService.encode("text")).thenReturn(WORD_TEXT_IN_MD5_HASH);
        JsonArray actual = jsonEncoderService.encode(element.getAsJsonArray());
        assertThat(actual).isEqualTo(readFromFile("json_array_expected.json"));
    }

    @SneakyThrows
    private JsonElement readFromFile(String path) {
        File file = ResourceUtils.getFile("classpath:" + path);
        return JsonParser.parseString(new String(Files.readAllBytes(file.toPath())));
    }
}