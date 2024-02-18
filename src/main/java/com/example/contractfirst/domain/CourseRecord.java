package com.example.contractfirst.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class CourseRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer courseId;
    @NotBlank(message = "The course courseName must be defined.")
    private String courseName;
    @NotNull(message = "The course courseDuration must be defined.")
    @Positive(message = "The course courseDuration must be greater then zero")
    private Integer courseDuration;
    @NotBlank(message = "The course courseType must be defined.")
    private String courseType;
}
