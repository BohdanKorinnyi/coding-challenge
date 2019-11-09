package com.edgile.codingchallenge.controller;

import com.edgile.codingchallenge.service.JsonEncoderService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.ResponseEntity.badRequest;

@ExtendWith(MockitoExtension.class)
class Md5EncoderControllerTest {
    @Mock
    private JsonEncoderService jsonEncoderService;
    @InjectMocks
    private Md5EncoderController md5EncoderController;

    @Test
    void encodeShouldReturnBadRequest() {
        ResponseEntity<Object> expected = badRequest().build();
        ResponseEntity<String> actual = md5EncoderController.encode("not valid json");
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void encodeShouldProcessJsonArray() {
        when(jsonEncoderService.encode(any(JsonArray.class))).thenReturn(new JsonArray());
        ResponseEntity<String> actual = md5EncoderController.encode("[1,3,4, {\"key\": 1}]");
        verify(jsonEncoderService).encode(any(JsonArray.class));
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void encodeShouldProcessJsonObject() {
        when(jsonEncoderService.encode(any(JsonObject.class))).thenReturn(new JsonObject());
        ResponseEntity<String> actual = md5EncoderController.encode("{\"key\": 1}");
        verify(jsonEncoderService).encode(any(JsonObject.class));
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}