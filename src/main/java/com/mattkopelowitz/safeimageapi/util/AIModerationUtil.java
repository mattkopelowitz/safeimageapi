package com.mattkopelowitz.safeimageapi.util;

import com.mattkopelowitz.safeimageapi.dto.ModerationResult;
import com.mattkopelowitz.safeimageapi.dto.ModerationResult.LabelResult;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.rekognition.RekognitionClient;
import software.amazon.awssdk.services.rekognition.model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class AIModerationUtil {

    private final RekognitionClient rekognitionClient = RekognitionClient.builder()
            .region(Region.US_EAST_2)
            .credentialsProvider(EnvironmentVariableCredentialsProvider.create())
            .build();

    public ModerationResult analyze(MultipartFile file) throws IOException {
        ModerationResult result = new ModerationResult();

        Image image = Image.builder()
                .bytes(SdkBytes.fromInputStream(file.getInputStream()))
                .build();

        DetectModerationLabelsResponse moderationResponse = rekognitionClient.detectModerationLabels(
                DetectModerationLabelsRequest.builder()
                        .image(image)
                        .minConfidence(70f)
                        .build());

        for (ModerationLabel label : moderationResponse.moderationLabels()) {
            String name = label.name().toLowerCase();
            float confidence = label.confidence();

            if (name.contains("explicit") || name.contains("nudity") || name.contains("sexual")) {
                result.setNsfw(true);
                result.setNsfwConfidence(confidence);
            } else if (name.contains("violence")) {
                result.setViolent(true);
                result.setViolenceConfidence(confidence);
            } else if (name.contains("gore")) {
                result.setGory(true);
                result.setGoreConfidence(confidence);
            } else if (name.contains("drugs") || name.contains("alcohol")) {
                result.setDrugsAndAlcohol(true);
                result.setDrugsAndAlcoholConfidence(confidence);
            }
        }

        DetectLabelsResponse labelResponse = rekognitionClient.detectLabels(
                DetectLabelsRequest.builder()
                        .image(image)
                        .maxLabels(10)
                        .minConfidence(70f)
                        .build());

        List<LabelResult> tags = new ArrayList<>();
        for (Label label : labelResponse.labels()) {
            tags.add(new LabelResult(label.name(), label.confidence()));
        }

        result.setTags(tags);
        return result;
    }
}