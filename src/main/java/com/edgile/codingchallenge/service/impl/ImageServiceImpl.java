package com.edgile.codingchallenge.service.impl;

import com.edgile.codingchallenge.service.ImageService;
import com.edgile.codingchallenge.service.S3Service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final S3Service s3Service;

    @Override
    public boolean compare(String path1, String path2) {
        Optional<String> eTag1 = s3Service.getETag(path1);
        Optional<String> eTag2 = s3Service.getETag(path2);
        if (eTag1.isEmpty() || eTag2.isEmpty()) {
            log.info("Image(s) not found by keys({}: {}, {}: {})", path1, eTag1, path2, eTag2);
            return false;
        }
        return eTag1.get().equals(eTag2.get());
    }

    @Override
    public List<String> findIdentical(List<String> paths) {
        return groupIdenticalImagesPaths(paths).values()
                .stream()
                .filter(strings -> strings.size() > 1)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private Map<String, List<String>> groupIdenticalImagesPaths(List<String> paths) {
        Map<String, List<String>> map = new HashMap<>();
        for (String path : paths) {
            Optional<String> eTag = s3Service.getETag(path);
            if (eTag.isPresent()) {
                if (map.containsKey(eTag.get())) {
                    map.get(eTag.get()).add(path);
                } else {
                    List<String> internalPaths = new ArrayList<>();
                    internalPaths.add(path);
                    map.put(eTag.get(), internalPaths);
                }
            }
        }
        return map;
    }
}
