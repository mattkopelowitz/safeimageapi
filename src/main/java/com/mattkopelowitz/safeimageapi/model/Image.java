package com.mattkopelowitz.safeimageapi.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String originalFilename;
    private String s3Key;
    private String s3Url;

    private LocalDateTime uploadedAt;

    // Moderation Flags
    private Boolean isNsfw;
    private Boolean isViolent;
    private Boolean isGory;
    private Boolean isDrugsAndAlcohol;

    private Double nsfwConfidence;
    private Double violenceConfidence;
    private Double goreConfidence;
    private Double drugsAndAlcoholConfidence;

    @OneToMany(mappedBy = "image", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImageTag> tags;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
    }

    public String getS3Key() {
        return s3Key;
    }

    public void setS3Key(String s3Key) {
        this.s3Key = s3Key;
    }

    public String getS3Url() {
        return s3Url;
    }

    public void setS3Url(String s3Url) {
        this.s3Url = s3Url;
    }

    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(LocalDateTime uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    public Boolean getIsNsfw() {
        return isNsfw;
    }

    public void setIsNsfw(Boolean isNsfw) {
        this.isNsfw = isNsfw;
    }

    public Boolean getIsViolent() {
        return isViolent;
    }

    public void setIsViolent(Boolean isViolent) {
        this.isViolent = isViolent;
    }

    public Boolean getIsGory() {
        return isGory;
    }

    public void setIsGory(Boolean isGory) {
        this.isGory = isGory;
    }

    public Boolean getIsDrugsAndAlcohol() {
        return isDrugsAndAlcohol;
    }

    public void setIsDrugsAndAlcohol(Boolean isDrugsAndAlcohol) {
        this.isDrugsAndAlcohol = isDrugsAndAlcohol;
    }

    public Double getNsfwConfidence() {
        return nsfwConfidence;
    }

    public void setNsfwConfidence(Double nsfwConfidence) {
        this.nsfwConfidence = nsfwConfidence;
    }

    public Double getViolenceConfidence() {
        return violenceConfidence;
    }

    public void setViolenceConfidence(Double violenceConfidence) {
        this.violenceConfidence = violenceConfidence;
    }

    public Double getGoreConfidence() {
        return goreConfidence;
    }

    public void setGoreConfidence(Double goreConfidence) {
        this.goreConfidence = goreConfidence;
    }

    public Double getDrugsAndAlcoholConfidence() {
        return drugsAndAlcoholConfidence;
    }

    public void setDrugsAndAlcoholConfidence(Double drugsAndAlcoholConfidence) {
        this.drugsAndAlcoholConfidence = drugsAndAlcoholConfidence;
    }

    public List<ImageTag> getTags() {
        return tags;
    }

    public void setTags(List<ImageTag> tags) {
        this.tags = tags;
    }
}
