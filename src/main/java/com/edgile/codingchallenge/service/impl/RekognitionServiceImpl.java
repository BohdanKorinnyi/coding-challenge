package com.edgile.codingchallenge.service.impl;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.model.DetectLabelsRequest;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.rekognition.model.Label;
import com.amazonaws.services.rekognition.model.S3Object;
import com.amazonaws.services.s3.AmazonS3URI;
import com.edgile.codingchallenge.service.RekognitionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class RekognitionServiceImpl implements RekognitionService {

    private final AmazonRekognition amazonRekognition;

    @Override
    public List<Label> detectObjects(AmazonS3URI uri) {
        try {
            log.info("Detecting labels on the object: {}/{}", uri.getBucket(), uri.getKey());
            return amazonRekognition.detectLabels(createDetectLabelsRequest(uri))
                    .getLabels();
        } catch (Exception e) {
            log.error("An error occurred while recognizing object content {}/{}", uri.getBucket(), uri.getKey(), e);
        }
        return Collections.emptyList();
    }

    private DetectLabelsRequest createDetectLabelsRequest(AmazonS3URI uri) {
        return new DetectLabelsRequest()
                .withImage(createImage(uri));
    }

    private Image createImage(AmazonS3URI uri) {
        return new Image()
                .withS3Object(createS3Object(uri));
    }

    private S3Object createS3Object(AmazonS3URI uri) {
        return new S3Object()
                .withBucket(uri.getBucket())
                .withName(uri.getKey());
    }
}
