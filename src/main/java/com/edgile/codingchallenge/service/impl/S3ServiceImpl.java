package com.edgile.codingchallenge.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3URI;
import com.edgile.codingchallenge.service.S3Service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class S3ServiceImpl implements S3Service {

    private final AmazonS3 amazonS3;

    @Override
    public Optional<String> getETag(AmazonS3URI uri) {
        try {
            return Optional.of(amazonS3.getObjectMetadata(uri.getBucket(), uri.getKey()).getETag());
        } catch (Exception e) {
            log.error("An error occurred while fetching object metadata from bucket: {}, key: {}", uri.getBucket(), uri.getKey(), e);
        }
        return Optional.empty();
    }
}
