package com.example.contractfirst.domain;

import java.util.Optional;

public interface StudentRepository {
    Iterable<StudentRecord> findAll();
    Optional<StudentRecord> findById(Integer id);
    boolean existsById(Integer id);
    void deleteById(Integer id);

    StudentRecord save(StudentRecord record);
}
