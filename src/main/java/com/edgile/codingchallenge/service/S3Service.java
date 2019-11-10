package com.edgile.codingchallenge.service;

import com.amazonaws.services.s3.AmazonS3URI;

import java.util.Optional;

public interface S3Service {
    Optional<String> getETag(AmazonS3URI uri);
}
