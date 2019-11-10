package com.edgile.codingchallenge.controller;

import com.edgile.codingchallenge.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@AllArgsConstructor
@RequestMapping("images")
public class ImageController {
    private final ImageService imageService;

    @PostMapping("compare")
    public ResponseEntity<Boolean> compare(@RequestParam String firstUrl, @RequestParam String secondUrl) {
        return ok(imageService.compare(firstUrl, secondUrl));
    }

    @GetMapping("identical")
    public ResponseEntity<Map<String, List<String>>> findIdentical(@RequestParam List<String> paths) {
        return ok(imageService.findIdentical(paths));
    }
}
