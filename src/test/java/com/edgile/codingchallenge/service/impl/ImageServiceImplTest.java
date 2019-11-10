package com.edgile.codingchallenge.service.impl;

import com.amazonaws.services.s3.AmazonS3URI;
import com.edgile.codingchallenge.service.S3Service;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ImageServiceImplTest {

    private static final String S3_URL_1 = "http://bucket.s3.amazonaws.com/object-key-1";
    private static final String S3_URL_2 = "http://bucket.s3.amazonaws.com/object-key-2";

    @Mock
    private S3Service s3Service;
    @InjectMocks
    private ImageServiceImpl imageService;

    @Test
    void compareShouldReturnFalse() {
        when(s3Service.getETag(new AmazonS3URI(S3_URL_1))).thenReturn(Optional.of("checksum_1"));
        when(s3Service.getETag(new AmazonS3URI(S3_URL_2))).thenReturn(Optional.empty());
        assertThat(imageService.compare(S3_URL_1, S3_URL_2)).isFalse();
    }

    @Test
    void compareShouldReturnTrue() {
        when(s3Service.getETag(new AmazonS3URI(S3_URL_1))).thenReturn(Optional.of("checksum_1"));
        when(s3Service.getETag(new AmazonS3URI(S3_URL_2))).thenReturn(Optional.of("checksum_1"));
        assertThat(imageService.compare(S3_URL_1, S3_URL_2)).isTrue();
    }

    @Test
    void findIdenticalShouldReturnOneChecksumWithUrls() {
        String checksum = "checksum";
        when(s3Service.getETag(new AmazonS3URI(S3_URL_1))).thenReturn(Optional.of(checksum));
        when(s3Service.getETag(new AmazonS3URI(S3_URL_2))).thenReturn(Optional.of(checksum));
        Map<String, List<String>> identical = imageService.findIdentical(List.of(S3_URL_1, S3_URL_2));
        assertThat(identical).isNotEmpty();
        assertThat(identical).containsOnlyKeys(checksum);
        assertThat(identical.get(checksum)).containsExactlyInAnyOrder(S3_URL_1, S3_URL_2);
    }

    @Test
    void findIdenticalShouldReturnTwoChecksumWithUrl() {
        String checksum1 = "checksum_1";
        String checksum2 = "checksum_2";
        when(s3Service.getETag(new AmazonS3URI(S3_URL_1))).thenReturn(Optional.of(checksum1));
        when(s3Service.getETag(new AmazonS3URI(S3_URL_2))).thenReturn(Optional.of(checksum2));
        Map<String, List<String>> identical = imageService.findIdentical(List.of(S3_URL_1, S3_URL_2));
        assertThat(identical).isNotEmpty();
        assertThat(identical).containsOnlyKeys(checksum1, checksum2);
        assertThat(identical.get(checksum1)).containsOnly(S3_URL_1);
        assertThat(identical.get(checksum2)).containsOnly(S3_URL_2);
    }
}