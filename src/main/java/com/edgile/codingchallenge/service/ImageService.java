package com.edgile.codingchallenge.service;

import java.util.List;

public interface ImageService {
    boolean compare(String path1, String path2);

    List<String> findIdentical(List<String> paths);
}
