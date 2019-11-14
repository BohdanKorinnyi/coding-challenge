package com.edgile.codingchallenge.service.impl;

import com.amazonaws.services.s3.AmazonS3URI;
import com.edgile.codingchallenge.service.ImageService;
import com.edgile.codingchallenge.service.S3Service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final S3Service s3Service;

    /**
     * Analyzes whether images are identical
     *
     * @param path1 represents a path to an image stored in an S3 bucket
     * @param path2 represents a path to an image stored in an S3 bucket
     * @return true if images are identical otherwise false
     */
    @Override
    public boolean compare(String path1, String path2) {
        Optional<String> eTag1 = s3Service.getETag(new AmazonS3URI(path1));
        Optional<String> eTag2 = s3Service.getETag(new AmazonS3URI(path2));
        if (eTag1.isEmpty() || eTag2.isEmpty()) {
            log.info("Image(s) not found by keys({}: {}, {}: {})", path1, eTag1, path2, eTag2);
            return false;
        }
        return eTag1.get().equals(eTag2.get());
    }

    /**
     * Analyzes which images are identical in the list
     *
     * @param paths represents the n number of images stored in an S3 bucket.
     * @return map where the key is the checksum and the value is the list of paths to identical files
     */
    @Override
    public Map<String, List<String>> findIdentical(List<String> paths) {
        Map<String, List<String>> map = new HashMap<>();
        for (String path : paths) {
            Optional<String> eTag = s3Service.getETag(new AmazonS3URI(path));
            if (eTag.isPresent()) {
                map.computeIfPresent(eTag.get(), (checksum, internalPaths) -> {
                    internalPaths.add(path);
                    return internalPaths;
                });
                map.computeIfAbsent(eTag.get(), checksum -> {
                    List<String> internalPaths = new ArrayList<>();
                    internalPaths.add(path);
                    return internalPaths;
                });
            }
        }
        return map;
    }
}
