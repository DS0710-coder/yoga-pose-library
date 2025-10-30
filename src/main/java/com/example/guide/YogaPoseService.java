package com.example.guide;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service // Marks this as the Service layer
public class YogaPoseService {

    @Autowired // Automatically connects this to the repository
    private YogaPoseRepository yogaPoseRepository;

    // Get all poses
    public List<YogaPose> getAllPoses() {
        return yogaPoseRepository.findAll();
    }

    // Get a single pose by its ID
    public Optional<YogaPose> getPoseById(Long id) {
        return yogaPoseRepository.findById(id);
    }

    // Create a new pose
    public YogaPose createPose(YogaPose pose) {
        return yogaPoseRepository.save(pose);
    }

    // Update a pose
    public YogaPose updatePose(Long id, YogaPose poseDetails) {
        // Find the existing pose
        YogaPose pose = yogaPoseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pose not found with id: " + id));

        // Update its fields
        pose.setSanskritName(poseDetails.getSanskritName());
        pose.setEnglishName(poseDetails.getEnglishName());
        pose.setDescription(poseDetails.getDescription());
        pose.setDifficultyLevel(poseDetails.getDifficultyLevel());
        pose.setImageUrl(poseDetails.getImageUrl());

        // Save the updated pose back to the database
        return yogaPoseRepository.save(pose);
    }

    // Delete a pose
    public void deletePose(Long id) {
        yogaPoseRepository.deleteById(id);
    }
}
