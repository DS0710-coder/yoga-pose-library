package com.example.guide;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Marks this as a Spring Data repository
public interface YogaPoseRepository extends JpaRepository<YogaPose, Long> {
    // Spring Data JPA automatically creates methods like:
    // - save(YogaPose pose)
    // - findById(Long id)
    // - findAll()
    // - deleteById(Long id)
}
