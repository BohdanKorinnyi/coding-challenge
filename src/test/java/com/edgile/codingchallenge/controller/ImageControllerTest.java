package com.edgile.codingchallenge.controller;

import com.edgile.codingchallenge.service.ImageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ImageControllerTest {

    @Mock
    private ImageService imageService;
    @InjectMocks
    private ImageController imageController;

    @Test
    void compare() {
        when(imageService.compare(any(String.class), any(String.class))).thenReturn(false);
        ResponseEntity<Boolean> response = imageController.compare("test1", "test2");
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isFalse();
    }

    @Test
    void findIdentical() {
        when(imageService.findIdentical(anyList())).thenReturn(Collections.emptyList());
        ResponseEntity<List<String>> response = imageController.findIdentical(List.of("test1", "test2"));
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEmpty();
    }
}