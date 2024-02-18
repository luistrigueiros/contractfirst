package com.example.contractfirst.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class StudentRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentId;
    //@NotBlank(message = "The student firstName must be defined.")
    private String firstName;
    //@NotBlank(message = "The student lastName must be defined.")
    private String lastName;
    private String phoneNumber;
    private String address;
}
