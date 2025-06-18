package com.mattkopelowitz.safeimageapi.dto;

import com.mattkopelowitz.safeimageapi.model.Image;
import com.mattkopelowitz.safeimageapi.model.ImageTag;
import java.util.List;

public class ImageUploadResponse {
    public Long id;
    public String originalFilename;
    public String s3Url;
    public boolean isNsfw, isViolent, isGory, isDrugsAndAlcohol;
    public double nsfwConfidence, violenceConfidence, goreConfidence, drugsAndAlcoholConfidence;
    public List<String> tags;

    public ImageUploadResponse(Image image) {
        this.id = image.getId();
        this.originalFilename = image.getOriginalFilename();
        this.s3Url = image.getS3Url();
        this.isNsfw = Boolean.TRUE.equals(image.getIsNsfw());
        this.isViolent = Boolean.TRUE.equals(image.getIsViolent());
        this.isGory = Boolean.TRUE.equals(image.getIsGory());
        this.isDrugsAndAlcohol = Boolean.TRUE.equals(image.getIsDrugsAndAlcohol());
        this.nsfwConfidence = image.getNsfwConfidence();
        this.violenceConfidence = image.getViolenceConfidence();
        this.goreConfidence = image.getGoreConfidence();
        this.drugsAndAlcoholConfidence = image.getDrugsAndAlcoholConfidence();
        this.tags = image.getTags().stream().map(ImageTag::getTag).toList();
    }
}
