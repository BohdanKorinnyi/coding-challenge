package com.edgile.codingchallenge.service;

import java.util.Optional;

public interface S3Service {
    Optional<String> getETag(String key);
}
