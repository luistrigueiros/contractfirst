package com.example.contractfirst.domain;

import jakarta.validation.constraints.NotBlank;

public record CourseRecord(
        @NotBlank(message = "The course courseId must be defined.")
        Integer courseId,
        @NotBlank(message = "The course courseName must be defined.")
        String courseName,
        @NotBlank(message = "The course courseDuration must be defined.")
        Integer courseDuration,
        @NotBlank(message = "The course courseType must be defined.")
        String courseType
) {}
