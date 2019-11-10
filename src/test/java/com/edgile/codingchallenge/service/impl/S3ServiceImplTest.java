package com.edgile.codingchallenge.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3URI;
import com.amazonaws.services.s3.Headers;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class S3ServiceImplTest {
    private static final String S3_URL = "http://bucket.s3.amazonaws.com/object-key-1";

    @Mock
    private AmazonS3 amazonS3;
    @InjectMocks
    private S3ServiceImpl s3Service;

    @Test
    void getETag() {
        String checksum = "checksum";
        AmazonS3URI uri = new AmazonS3URI(S3_URL);
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setHeader(Headers.ETAG, checksum);
        when(amazonS3.getObjectMetadata(uri.getBucket(), uri.getKey())).thenReturn(metadata);
        Optional<String> eTag = s3Service.getETag(uri);
        assertThat(eTag).isPresent();
        assertThat(eTag.get()).isEqualTo(checksum);
    }

    @Test
    void getETagThrowsException() {
        AmazonS3URI uri = new AmazonS3URI(S3_URL);
        when(amazonS3.getObjectMetadata(uri.getBucket(), uri.getKey())).thenThrow(RuntimeException.class);
        Optional<String> eTag = s3Service.getETag(uri);
        assertThat(eTag).isEmpty();
    }
}