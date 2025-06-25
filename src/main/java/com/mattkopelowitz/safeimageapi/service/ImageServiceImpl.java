package com.mattkopelowitz.safeimageapi.service;

import com.mattkopelowitz.safeimageapi.dto.ImageUploadResponse;
import com.mattkopelowitz.safeimageapi.dto.ModerationResult;
import com.mattkopelowitz.safeimageapi.model.Image;
import com.mattkopelowitz.safeimageapi.model.ImageTag;
import com.mattkopelowitz.safeimageapi.repository.ImageRepository;
import com.mattkopelowitz.safeimageapi.repository.ImageTagRepository;
import com.mattkopelowitz.safeimageapi.util.AIModerationUtil;
//import com.mattkopelowitz.safeimageapi.util.S3Uploader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ImageTagRepository imageTagRepository;

    @Autowired
    private S3Uploader s3Uploader;

    @Autowired
    private AIModerationUtil moderationUtil;

    @Override
    public ImageUploadResponse processAndStoreImage(MultipartFile file) {
        try {
            // 1. Upload to S3
            String s3Key = s3Uploader.uploadFile(file);
            String s3Url = s3Uploader.getPublicUrl(s3Key);

            // 2. Analyze with AI
            ModerationResult moderationResult = moderationUtil.analyze(file);

            // 3. Create and persist image metadata
            Image image = new Image();
            image.setOriginalFilename(file.getOriginalFilename());
            image.setS3Key(s3Key);
            image.setS3Url(s3Url);
            image.setUploadedAt(LocalDateTime.now());

            image.setIsNsfw(moderationResult.isNsfw());
            image.setIsViolent(moderationResult.isViolent());
            image.setIsGory(moderationResult.isGory());
            image.setIsDrugsAndAlcohol(moderationResult.isDrugsAndAlcohol());

            image.setNsfwConfidence(moderationResult.getNsfwConfidence());
            image.setViolenceConfidence(moderationResult.getViolenceConfidence());
            image.setGoreConfidence(moderationResult.getGoreConfidence());
            image.setDrugsAndAlcoholConfidence(moderationResult.getDrugsAndAlcoholConfidence());

            image = imageRepository.save(image);

            // 4. Map and save AI-generated tags
            Image finalImage = image;
            List<ImageTag> tags = moderationResult.getTags().stream()
                    .map(tag -> new ImageTag(tag.getTag(), tag.getConfidence(), finalImage))
                    .toList();

            imageTagRepository.saveAll(tags);
            image.setTags(tags);

            // 5. Return DTO
            return new ImageUploadResponse(image);

        } catch (IOException e) {
            throw new RuntimeException("Failed to upload file", e);
        }
    }


    @Override
    public ResponseEntity<?> getImageMetadata(Long id) {
        return imageRepository.findById(id)
                .map(image -> ResponseEntity.ok(new ImageUploadResponse(image)))
                .orElse(ResponseEntity.notFound().build());
    }
}
