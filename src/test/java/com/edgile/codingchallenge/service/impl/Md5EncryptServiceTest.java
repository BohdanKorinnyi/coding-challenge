package com.edgile.codingchallenge.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.MessageDigest;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class Md5EncryptServiceTest {
    @Mock
    private MessageDigest messageDigest;
    @InjectMocks
    private Md5EncryptService md5EncryptService;

    @Test
    void encodeShouldReturnNull() {
        assertThat(md5EncryptService.encode(null)).isNull();
    }

    @Test
    void encodeShouldReturnNullIfEmpty() {
        assertThat(md5EncryptService.encode("")).isNull();
    }
}