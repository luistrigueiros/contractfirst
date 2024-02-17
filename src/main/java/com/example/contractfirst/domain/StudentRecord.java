package com.example.contractfirst.domain;

import jakarta.validation.constraints.NotBlank;

public record StudentRecord(
        @NotBlank(message = "The student studentId must be defined.")

        Integer studentId,

        @NotBlank(message = "The student firstName must be defined.")
        String firstName,

        @NotBlank(message = "The student lastName must be defined.")
        String lastName,

        String phoneNumber,

        String address

) { }
