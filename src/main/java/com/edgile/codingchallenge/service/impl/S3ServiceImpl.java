package com.edgile.codingchallenge.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.edgile.codingchallenge.configuration.AwsS3Configuration;
import com.edgile.codingchallenge.service.S3Service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class S3ServiceImpl implements S3Service {

    private final AwsS3Configuration awsS3Configuration;
    private final AmazonS3 amazonS3;

    @Override
    public Optional<String> getETag(String key) {
        try {
            return Optional.of(amazonS3.getObjectMetadata(awsS3Configuration.getBucket(), key).getETag());
        } catch (Exception e) {
            log.error("An error occurred while fetching object metadata: {}", key, e);
        }
        return Optional.empty();
    }
}
