package com.edgile.codingchallenge.service;

import java.util.List;
import java.util.Map;

public interface ImageService {
    boolean compare(String path1, String path2);

    Map<String, List<String>> findIdentical(List<String> paths);

    Map<String, List<String>> groupByContent(List<String> paths);
}
