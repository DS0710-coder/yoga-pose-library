package com.example.guide;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class GuideApplication {

    public static void main(String[] args) {
        // Print DATABASE_URL to verify it's being passed
        String dbUrl = System.getenv("DATABASE_URL");
        System.out.println("==========================================");
        System.out.println("DATABASE_URL is: " + (dbUrl != null ? "SET (length: " + dbUrl.length() + ")" : "NOT SET"));
        System.out.println("SPRING_PROFILES_ACTIVE: " + System.getenv("SPRING_PROFILES_ACTIVE"));
        System.out.println("==========================================");

        SpringApplication.run(GuideApplication.class, args);
    }
}