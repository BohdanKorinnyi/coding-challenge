package com.edgile.codingchallenge.controller;

import com.edgile.codingchallenge.service.JsonEncoderService;
import com.edgile.codingchallenge.utils.JsonUtils;
import com.google.gson.JsonElement;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static com.edgile.codingchallenge.utils.JsonUtils.parseString;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("encoding/md5")
public class Md5EncoderController {

    private final JsonEncoderService jsonEncoderService;

    /**
     * The endpoint accepts a String parameter, a parameter should represent a JSON object or array.
     * The code 200 is returned if the message is processed correctly
     * The code 404 is returned if the message can't be processed
     *
     * @param value represent JSON object or array
     * @return string that represents JSON object or array with encoded values
     */
    @PostMapping("encode")
    public ResponseEntity<String> encode(@RequestParam String value) {
        Optional<JsonElement> element = parseString(value);
        if (element.isEmpty() || !JsonUtils.isProcessable(element.get())) {
            return badRequest().build();
        }
        if (element.get().isJsonArray()) {
            return ok(jsonEncoderService.encode(element.get().getAsJsonArray()).toString());
        }
        return ok(jsonEncoderService.encode(element.get().getAsJsonObject()).toString());
    }
}
