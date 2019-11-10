package com.edgile.codingchallenge.service.impl;

import com.edgile.codingchallenge.service.ImageService;
import com.edgile.codingchallenge.service.S3Service;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ImageServiceImplTest {
    @Mock
    private S3Service s3Service;
    @InjectMocks
    private ImageService imageService;

    @Test
    void compare() {
        // TODO: implement when task is clarified
    }

    @Test
    void findIdentical() {
        // TODO: implement when task is clarified
    }
}