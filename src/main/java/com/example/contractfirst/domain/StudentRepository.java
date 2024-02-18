package com.example.contractfirst.domain;

import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<StudentRecord, Long>  {
}
