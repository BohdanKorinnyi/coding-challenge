package com.edgile.codingchallenge.service;

import com.amazonaws.services.rekognition.model.Label;
import com.amazonaws.services.s3.AmazonS3URI;

import java.util.List;

public interface RekognitionService {
    List<Label> detectObjects(AmazonS3URI uri);
}
