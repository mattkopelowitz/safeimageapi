package com.mattkopelowitz.safeimageapi.service;

import com.mattkopelowitz.safeimageapi.dto.ImageUploadResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    ImageUploadResponse processAndStoreImage(MultipartFile file);
    ResponseEntity<?> getImageMetadata(Long id);
}
