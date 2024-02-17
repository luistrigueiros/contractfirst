package com.example.contractfirst.persistance;

import com.example.contractfirst.domain.CourseRecord;
import com.example.contractfirst.domain.CourseRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryCourseRepository implements CourseRepository {
    private static final Map<Integer, CourseRecord> courseMap = new ConcurrentHashMap<>();

    @Override
    public Iterable<CourseRecord> findAll() {
        return courseMap.values();
    }

    @Override
    public Optional<CourseRecord> findById(Integer id) {
        return existsById(id) ? Optional.of(courseMap.get(id)) : Optional.empty();
    }

    @Override
    public boolean existsById(Integer id) {
        return courseMap.get(id) != null;
    }

    @Override
    public void deleteById(Integer id) {
        courseMap.remove(id);
    }

    @Override
    public CourseRecord save(CourseRecord record) {
        courseMap.put(record.courseId(), record);
        return record;
    }
}
