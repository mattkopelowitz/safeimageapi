package com.mattkopelowitz.safeimageapi.controller;

import com.mattkopelowitz.safeimageapi.dto.ImageUploadResponse;
import com.mattkopelowitz.safeimageapi.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<ImageUploadResponse> uploadImage(@RequestParam("file") MultipartFile file) {
        ImageUploadResponse response = imageService.processAndStoreImage(file);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getImageMetadata(@PathVariable Long id) {
        return imageService.getImageMetadata(id);
    }
}
