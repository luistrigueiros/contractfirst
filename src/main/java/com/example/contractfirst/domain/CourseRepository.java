package com.example.contractfirst.domain;

import java.util.Optional;

public interface CourseRepository {
    Iterable<CourseRecord> findAll();
    Optional<CourseRecord> findById(Integer id);
    boolean existsById(Integer id);
    void deleteById(Integer id);

    CourseRecord save(CourseRecord record);
}
