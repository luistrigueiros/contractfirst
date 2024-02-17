package com.example.contractfirst.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StudentRepository extends CrudRepository<StudentRecord, Long>  {
}
