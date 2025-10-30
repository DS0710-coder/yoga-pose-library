package com.example.guide;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import jakarta.persistence.Column;

@Entity // Tells Spring this is a table in the database
@Data   // Lombok: automatically creates getters, setters, constructors
public class YogaPose {

    @Id // Marks this as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use IDENTITY for PostgreSQL
    private Long id;

    private String sanskritName;
    private String englishName;

    @Column(length = 1000) // Make the description column larger
    private String description;

    private String difficultyLevel;

    @Column(length = 500) // Make the image URL column larger
    private String imageUrl;
}
