package com.example.contractfirst;

import com.example.openapi.CoursesApi;
import com.example.openapi.models.Course;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CoursesControllerApiImpl implements CoursesApi {
    @Override
    public ResponseEntity<List<Course>> getCourses(String sortBy) {
        return CoursesApi.super.getCourses(sortBy);
    }
}
