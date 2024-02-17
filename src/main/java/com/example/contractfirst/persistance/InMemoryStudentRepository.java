package com.example.contractfirst.persistance;

import com.example.contractfirst.domain.StudentRecord;
import com.example.contractfirst.domain.StudentRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryStudentRepository implements StudentRepository {
    private static final Map<Integer, StudentRecord> studentMap = new ConcurrentHashMap<>();

    @Override
    public Iterable<StudentRecord> findAll() {
        return studentMap.values();
    }

    @Override
    public Optional<StudentRecord> findById(Integer id) {
        return existsById(id) ? Optional.of(studentMap.get(id)) : Optional.empty();
    }

    @Override
    public boolean existsById(Integer id) {
        return studentMap.get(id) != null;
    }

    @Override
    public void deleteById(Integer id) {
        studentMap.remove(id);
    }

    @Override
    public StudentRecord save(StudentRecord record) {
        Integer key = record.studentId();
        studentMap.put(key, record);
        return record;
    }
}
