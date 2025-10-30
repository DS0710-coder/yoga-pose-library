package com.example.guide;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController // Marks this as a REST API controller
@RequestMapping("/api/v1/poses") // Base URL for all endpoints in this class
@CrossOrigin(origins = "*") // Allow requests from any frontend
public class YogaPoseController {

    @Autowired // Connects this to the service
    private YogaPoseService yogaPoseService;

    // GET /api/v1/poses
    @GetMapping
    public List<YogaPose> getAllPoses() {
        return yogaPoseService.getAllPoses();
    }

    // GET /api/v1/poses/{id}
    @GetMapping("/{id}")
    public ResponseEntity<YogaPose> getPoseById(@PathVariable Long id) {
        return yogaPoseService.getPoseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/v1/poses
    @PostMapping
    public YogaPose createPose(@RequestBody YogaPose pose) {
        return yogaPoseService.createPose(pose);
    }

    // PUT /api/v1/poses/{id}
    @PutMapping("/{id}")
    public YogaPose updatePose(@PathVariable Long id, @RequestBody YogaPose poseDetails) {
        return yogaPoseService.updatePose(id, poseDetails);
    }

    // DELETE /api/v1/poses/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePose(@PathVariable Long id) {
        yogaPoseService.deletePose(id);
        return ResponseEntity.ok().build();
    }
}
