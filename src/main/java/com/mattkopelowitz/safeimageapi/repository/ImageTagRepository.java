package com.mattkopelowitz.safeimageapi.repository;

import com.mattkopelowitz.safeimageapi.model.ImageTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageTagRepository extends JpaRepository<ImageTag, Long>{
    // Example custom query:
    // List<ImageTag> findByImageId(Long imageId);
}
